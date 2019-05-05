package com.github.btafarelo.autentia.prueba.courses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursesControllerTest {

    @Autowired
    private CoursesController controller;

    @Test
    public void insertNewCourse() {
        CourseTO to = new CourseTO();

        to.setActive(true);
        to.setHours(166);
        to.setTitle("" + System.currentTimeMillis());
        to.setLevel(new LevelTO(1));
        to.setTeacher(new TeacherTO(1));

        ResponseEntity<?> result = controller.add(to);

        assert result.getBody() instanceof CourseTO;

        to = (CourseTO) result.getBody();

        result = controller.get(to.getId());

        assert result.getBody() instanceof CourseTO;
    }

    @Test
    public void checkTitleUniqueConstraint() {

        int id = 1;

        ResponseEntity<?> result = controller.get(id);

        assert result.getBody() instanceof CourseTO;

        CourseTO to = (CourseTO) result.getBody();

        result = controller.add(to);

        assert result.getStatusCode().is4xxClientError();

        assert result.getBody() instanceof String;

        String message = (String) result.getBody();

        assert message.equals("{\"Message\": \"Course hasn't been created.\"}");
    }
}
