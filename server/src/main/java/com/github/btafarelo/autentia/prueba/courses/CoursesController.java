package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.IRestController;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CoursesController implements IRestController {

    public static final String ID_S_NOT_FOUND = "{\"Message\": \"id [%s] not found.\"}";
    public static final String COURSE_HASN_T_BEEN_CREATED = "{\"Message\": \"Course hasn't been created.\"}";
    public static final String FILE_HASN_T_BEEN_UPLOADED = "{\"Message\": \"File hasn't been uploaded.\"}";
    public static final int BUFFER_SIZE = 1024;
    @Autowired
    private CourseMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CourseTO>> list(
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") Integer page) {
        int offset = page * PAGE_SIZE;

        RowBounds rowBounds = new RowBounds(offset, PAGE_SIZE);

        HttpEntity response = ResponseEntity.EMPTY;

        List<CourseTO> responseBody = mapper.list(rowBounds, sort, order);

        String total = String.valueOf(mapper.count());

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", total);

        return new ResponseEntity<List<CourseTO>>(responseBody, headers, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Integer id) {
        CourseTO course = mapper.findById(id);

        if (course == null)
            return ResponseEntity.badRequest().body(String.format(ID_S_NOT_FOUND, id));

        return ResponseEntity.ok(course);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody CourseTO course) {
        try {
            mapper.insert(course);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(COURSE_HASN_T_BEEN_CREATED);
        }

        return ResponseEntity.ok(course);
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> upload(
            @RequestParam("id") String id,
            @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String type = file.getContentType();
            String name = file.getOriginalFilename();

            mapper.updateFile(Integer.parseInt(id), bytes, type, name);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(FILE_HASN_T_BEEN_UPLOADED);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/download/{id}"}, method = RequestMethod.GET)
    public void download(
            @PathVariable Integer id,
            HttpServletResponse response) {

        CourseTO course = mapper.findById(id);

        response.addHeader(HttpHeaders.CONTENT_TYPE, course.getContentType());
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + course.getFileName());
        response.addHeader(HttpHeaders.CONTENT_LENGTH, "" + course.getFile().length);

        try {
            ServletOutputStream out = null;
            ByteArrayInputStream bais = null;

            try {
                out = response.getOutputStream();
                bais = new ByteArrayInputStream(course.getFile());

                int len = 0;
                byte[] bytes = new byte[BUFFER_SIZE];

                while ((len = bais.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                    out.flush();
                }
            } finally {
                if (out != null)
                    out.close();

                if (bais != null)
                    bais.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
