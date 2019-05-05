package com.github.btafarelo.autentia.prueba.commons;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface IMapper<T extends TO> {

    T findById(@Param("id") int id);

    List<T> list(RowBounds rowBounds, @Param("sort") String sort, @Param("order") String order);

    void insert(T to);
}
