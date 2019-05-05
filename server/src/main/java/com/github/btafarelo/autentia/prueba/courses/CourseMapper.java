package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.IMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CourseMapper extends IMapper<CourseTO> {

    @Select({"SELECT * FROM courses WHERE id = #{id}"})
    @Results(value = {
        @Result(property = "level",
                column = "level",
                one = @One(select = "com.github.btafarelo.autentia.prueba.courses.LevelMapper.findById")),
        @Result(property = "teacher",
                column = "teacher",
                one = @One(select = "com.github.btafarelo.autentia.prueba.courses.TeacherMapper.findById"))
    })
    CourseTO findById(@Param("id") Integer id);

    @Select("SELECT * FROM courses WHERE active = true ORDER BY ${sort} ${order}")
    @Results(value = {
        @Result(property = "level",
                column = "level",
                one = @One(select = "com.github.btafarelo.autentia.prueba.courses.LevelMapper.findById")),
        @Result(property = "teacher",
                column = "teacher",
                one = @One(select = "com.github.btafarelo.autentia.prueba.courses.TeacherMapper.findById"))
    })
    List<CourseTO> list(RowBounds rowBounds, @Param("sort") String sort, @Param("order") String order);

    @Select("SELECT count(id) FROM courses WHERE active = true")
    Integer count();

    @Insert({"insert into courses (title, hours, active, level, teacher) values (#{title}, #{hours}, #{active}, #{level.id}, #{teacher.id})"})
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(CourseTO course);

    @Update({"update courses set file = #{file}, contentType = #{type}, fileName = #{name} where id = #{id}"})
    void updateFile(@Param("id") int id, @Param("file") byte[] bytes, @Param("type") String type, @Param("name") String name);

}
