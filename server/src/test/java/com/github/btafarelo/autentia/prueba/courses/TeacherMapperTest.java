package com.github.btafarelo.autentia.prueba.courses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper mapper;

    @Test
    public void checkIfExistsAnyTeacher() {
        assert mapper.list().size() > 0;
    }
}
