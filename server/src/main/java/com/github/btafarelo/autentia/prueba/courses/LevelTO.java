package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.TO;

public class LevelTO extends TO {

    private String value;

    public LevelTO() {

    }

    public LevelTO(int id) {
        super(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
