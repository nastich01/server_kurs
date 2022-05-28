package com.example.kpo.entity;

import java.sql.Date;

public class Activity extends BaseEntity{
    private Integer id_event;
    private Integer id_tip;
    private Integer id_direction;
    private String work_name;
    private String annotation;
    private Integer id_student;
    private Integer id_teacher;
    private String event_name;
    private Date event_date;

    //конструктор
    public Activity(Integer id, Integer id_event, Integer id_tip, Integer id_direction, String work_name,
                    String annotation, Integer id_student, Integer id_teacher,String event_name,Date event_date) {
        super(id);
        this.id_event = id_event;
        this.id_tip = id_tip;
        this.id_direction = id_direction;
        this.work_name = work_name;
        this.annotation = annotation;
        this.id_student = id_student;
        this.id_teacher = id_teacher;
        this.event_date = event_date;
        this.event_name = event_name;
    }

    //методы get и set
    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public Integer getId_tip() {
        return id_tip;
    }

    public void setId_tip(Integer id_tip) {
        this.id_tip = id_tip;
    }

    public Integer getId_direction() {
        return id_direction;
    }

    public void setId_direction(Integer id_direction) {
        this.id_direction = id_direction;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public Integer getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Integer id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }
}
