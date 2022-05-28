package com.example.kpo.entity;

public class Faculty extends BaseEntity{
    private String faculty_name;

    public Faculty(Integer id, String faculty_name) {
        super(id);
        this.faculty_name = faculty_name;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }
}
