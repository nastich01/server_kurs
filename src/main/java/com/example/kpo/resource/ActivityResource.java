package com.example.kpo.resource;

import com.example.kpo.entity.Activity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;

public class ActivityResource extends BaseResource{
    private Integer id;
    private Integer id_event;
    private Integer id_tip;
    private Integer id_direction;
    private String work_name;
    private String annotation;
    private Integer id_student;
    private Integer id_teacher;
    private String event_name;
    private Date event_date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EventResource eventResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TipResource tipResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DirectionResource directionResource;

    /*@JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherActivityResource[] teacherActivityResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentActivityResource[] studentActivityResource;*/

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentResource studentResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherResource teacherResource;

    public ActivityResource() {}

    public ActivityResource(Activity item) {
        this.id = item.getId();
        this.id_event = item.getId_event();
        this.id_tip = item.getId_tip();
        this.id_direction = item.getId_direction();
        this.work_name = item.getWork_name();
        this.annotation = item.getAnnotation();
        this.id_student = item.getId_student();
        this.id_teacher = item.getId_teacher();
        this.event_name = item.getEvent_name();
        this.event_date = item.getEvent_date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public EventResource getEventResource() {
        return eventResource;
    }

    public void setEventResource(EventResource eventResource) {
        this.eventResource = eventResource;
    }

    public TipResource getTipResource() {
        return tipResource;
    }

    public void setTipResource(TipResource tipResource) {
        this.tipResource = tipResource;
    }

    public DirectionResource getDirectionResource() {
        return directionResource;
    }

    public void setDirectionResource(DirectionResource directionResource) {
        this.directionResource = directionResource;
    }

    /*public TeacherActivityResource[] getTeacherActivityResource() {
        return teacherActivityResource;
    }

    public void setTeacherActivityResource(TeacherActivityResource[] teacherActivityResource) {
        this.teacherActivityResource = teacherActivityResource;
    }

    public StudentActivityResource[] getStudentActivityResource() {
        return studentActivityResource;
    }

    public void setStudentActivityResource(StudentActivityResource[] studentActivityResource) {
        this.studentActivityResource = studentActivityResource;
    }*/

    public StudentResource getStudentResource() {
        return studentResource;
    }

    public void setStudentResource(StudentResource studentResource) {
        this.studentResource = studentResource;
    }

    public TeacherResource getTeacherResource() {
        return teacherResource;
    }

    public void setTeacherResource(TeacherResource teacherResource) {
        this.teacherResource = teacherResource;
    }

    public Activity toEntity() {
        return new Activity(
                this.id,
                this.id_event,
                this.id_tip,
                this.id_direction,
                this.work_name,
                this.annotation,
                this.id_student,
                this.id_teacher,
                this.event_name,
                this.event_date
        );
    }
}
