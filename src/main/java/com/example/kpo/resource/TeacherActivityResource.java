package com.example.kpo.resource;

import com.example.kpo.entity.Student;
import com.example.kpo.entity.TeacherActivity;
import com.fasterxml.jackson.annotation.JsonInclude;

public class TeacherActivityResource extends BaseResource{
    private Integer id;
    private Integer id_activity;
    private Integer id_teacher;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource activityResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherResource teacherResource;

    public TeacherActivityResource() {}

    public TeacherActivityResource(TeacherActivity teacherActivity) {
        this.id = teacherActivity.getId();
        this.id_activity = teacherActivity.getId_activity();
        this.id_teacher = teacherActivity.getId_teacher();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ActivityResource getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource activityResource) {
        this.activityResource = activityResource;
    }

    public TeacherResource getTeacherResource() {
        return teacherResource;
    }

    public void setTeacherResource(TeacherResource teacherResource) {
        this.teacherResource = teacherResource;
    }

    public TeacherActivity toEntity() {
        return new TeacherActivity(
                this.id,
                this.id_activity,
                this.id_teacher
        );
    }
}
