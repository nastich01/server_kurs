package com.example.kpo.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.kpo.entity.Faculty;

public class FacultyResource extends BaseResource{
    private Integer id;
    private String faculty_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentResource[] studentResource;

    public FacultyResource() {}

    public FacultyResource(Faculty faculty) {
        this.id = faculty.getId();
        this.faculty_name = faculty.getFaculty_name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public StudentResource[] getStudentResource() {
        return studentResource;
    }

    public void setStudentResource(StudentResource[] studentResource) {
        this.studentResource = studentResource;
    }

    public Faculty toEntity() {
        return new Faculty(
                this.id,
                this.faculty_name
        );
    }
}
