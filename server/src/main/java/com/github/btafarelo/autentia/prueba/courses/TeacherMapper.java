package com.github.btafarelo.autentia.prueba.courses;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Component
@Mapper
public interface TeacherMapper {

    @Select({"SELECT * FROM teachers WHERE id = #{id}"})
    TeacherTO findById(@Param("id") Integer id);

    @Select({"SELECT * FROM teachers"})
    List<TeacherTO> list();
}
