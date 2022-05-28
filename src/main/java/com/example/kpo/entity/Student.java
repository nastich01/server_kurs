package com.example.kpo.entity;

public class Student extends BaseEntity{
    private String fio_stud;
    private Integer kurs;
    private String group;
    private Integer id_faculty;

    public Student(Integer id, String fio_stud, Integer kurs, String group, Integer id_faculty) {
        super(id);
        this.fio_stud = fio_stud;
        this.kurs = kurs;
        this.group = group;
        this.id_faculty = id_faculty;
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
}
