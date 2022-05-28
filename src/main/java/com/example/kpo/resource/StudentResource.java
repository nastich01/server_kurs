package com.example.kpo.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.kpo.entity.Student;

public class StudentResource extends BaseResource{
    private Integer id;
    private String fio_stud;
    private Integer kurs;
    private String group;
    private Integer id_faculty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FacultyResource facultyResource;

    /*@JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentActivityResource[] studentActivityResource;*/

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource[] activityResource;


    public StudentResource() {}

    public StudentResource(Student item) {
        this.id = item.getId();
        this.fio_stud = item.getFio_stud();
        this.kurs = item.getKurs();
        this.group = item.getGroup();
        this.id_faculty = item.getId_faculty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio_stud() {
        return fio_stud;
    }

    public void setFio_stud(String fio_stud) {
        this.fio_stud = fio_stud;
    }

    public Integer getKurs() {
        return kurs;
    }

    public void setKurs(Integer kurs) {
        this.kurs = kurs;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(Integer id_faculty) {
        this.id_faculty = id_faculty;
    }

    public FacultyResource getFacultyResource() {
        return facultyResource;
    }

    public void setFacultyResource(FacultyResource facultyResource) {
        this.facultyResource = facultyResource;
    }

    /*public StudentActivityResource[] getStudentActivityResource() {
        return studentActivityResource;
    }

    public void setStudentActivityResource(StudentActivityResource[] studentActivityResource) {
        this.studentActivityResource = studentActivityResource;
    }*/

    public ActivityResource[] getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource[] activityResource) {
        this.activityResource = activityResource;
    }

    public Student toEntity() {
        return new Student(
                this.id,
                this.fio_stud,
                this.kurs,
                this.group,
                this.id_faculty
        );
    }
}

