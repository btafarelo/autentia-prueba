package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.IRestController;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profesores",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController implements IRestController {

    @Autowired
    private TeacherMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TeacherTO>> list() {
        List<TeacherTO> responseBody = mapper.list();

        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Integer id) {
        TeacherTO course = mapper.findById(id);

        if (course == null)
            return ResponseEntity.badRequest().body(String.format("{Message: id [%s] not found}", id));

        return ResponseEntity.ok(course);
    }
}
