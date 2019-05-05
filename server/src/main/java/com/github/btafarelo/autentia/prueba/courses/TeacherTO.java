package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.TO;

public class TeacherTO extends TO {

    private String name;

    private String surname;

    public TeacherTO() {
    }

    public TeacherTO(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
