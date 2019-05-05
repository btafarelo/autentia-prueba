package com.github.btafarelo.autentia.prueba.courses;

import com.github.btafarelo.autentia.prueba.commons.TO;

public class CourseTO extends TO {

    private String title;

    private LevelTO level;

    private int hours;

    private boolean active;

    private TeacherTO teacher;

    private byte[] file;

    private String contentType;

    private String fileName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LevelTO getLevel() {
        return level;
    }

    public void setLevel(LevelTO level) {
        this.level = level;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TeacherTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherTO teacher) {
        this.teacher = teacher;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
