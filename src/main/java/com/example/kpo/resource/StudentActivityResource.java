package com.example.kpo.resource;

import com.example.kpo.entity.StudentActivity;
import com.fasterxml.jackson.annotation.JsonInclude;

public class StudentActivityResource extends BaseResource{
    private Integer id;
    private Integer id_activity;
    private Integer id_student;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource activityResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentResource studentResource;

    public StudentActivityResource() {}

    public StudentActivityResource(StudentActivity studentActivity) {
        this.id = studentActivity.getId();
        this.id_activity = studentActivity.getId_activity();
        this.id_student = studentActivity.getId_student();
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

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public ActivityResource getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource activityResource) {
        this.activityResource = activityResource;
    }

    public StudentResource getStudentResource() {
        return studentResource;
    }

    public void setStudentResource(StudentResource studentResource) {
        this.studentResource = studentResource;
    }

    public StudentActivity toEntity() {
        return new StudentActivity(
                this.id,
                this.id_activity,
                this.id_student
        );
    }
}
