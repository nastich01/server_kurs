package com.example.kpo.entity;

public class TeacherActivity extends BaseEntity{
    private Integer id_activity;
    private Integer id_teacher;

    public TeacherActivity(Integer id, Integer id_activity, Integer id_teacher) {
        super(id);
        this.id_activity = id_activity;
        this.id_teacher = id_teacher;
    }

    public Integer getId_activity() {
        return id_activity;
    }

    public void setId_activity(Integer id_activity) {
        this.id_activity = id_activity;
    }

    public Integer getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Integer id_teacher) {
        this.id_teacher = id_teacher;
    }
}
