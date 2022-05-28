package com.example.kpo.entity;

public class StudentActivity extends BaseEntity{
    private Integer id_activity;
    private Integer id_student;

    public StudentActivity(Integer id, Integer id_activity, Integer id_student) {
        super(id);
        this.id_activity = id_activity;
        this.id_student = id_student;
    }

    public Integer getId_activity() {
        return id_activity;
    }

    public void setId_activity(Integer id_activity) {
        this.id_activity = id_activity;
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }
}
