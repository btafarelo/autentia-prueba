package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LevelMapper extends IMapper<LevelTO> {

    @Select({"SELECT * FROM levels WHERE id = #{id}"})
    LevelTO findById(@Param("id") int id);

    @Select({"SELECT * FROM levels"})
    List<LevelTO> list();
}
