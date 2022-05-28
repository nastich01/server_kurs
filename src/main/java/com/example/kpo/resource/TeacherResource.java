package com.example.kpo.resource;

import com.example.kpo.entity.Student;
import com.example.kpo.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;

public class TeacherResource extends BaseResource{
    private Integer id;
    private String fio_teacher;
    private Integer id_dolzh;
    private Integer id_kafedra;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DolzhResource dolzhResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private KafedraResource kafedraResource;

   /* @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherActivityResource teacherActivityResource;*/

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivityResource[] activityResource;

    public TeacherResource() {}

    public TeacherResource(Teacher item) {
        this.id = item.getId();
        this.fio_teacher = item.getFio_teacher();
        this.id_dolzh = item.getId_dolzh();
        this.id_kafedra = item.getId_kafedra();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio_teacher() {
        return fio_teacher;
    }

    public void setFio_teacher(String fio_teacher) {
        this.fio_teacher = fio_teacher;
    }

    public Integer getId_dolzh() {
        return id_dolzh;
    }

    public void setId_dolzh(Integer id_dolzh) {
        this.id_dolzh = id_dolzh;
    }

    public Integer getId_kafedra() {
        return id_kafedra;
    }

    public void setId_kafedra(Integer id_kafedra) {
        this.id_kafedra = id_kafedra;
    }

    public DolzhResource getDolzhResource() {
        return dolzhResource;
    }

    public void setDolzhResource(DolzhResource dolzhResource) {
        this.dolzhResource = dolzhResource;
    }

    public KafedraResource getKafedraResource() {
        return kafedraResource;
    }

    public void setKafedraResource(KafedraResource kafedraResource) {
        this.kafedraResource = kafedraResource;
    }

    /*public TeacherActivityResource getTeacherActivityResource() {
        return teacherActivityResource;
    }

    public void setTeacherActivityResource(TeacherActivityResource teacherActivityResource) {
        this.teacherActivityResource = teacherActivityResource;
    }*/

    public ActivityResource[] getActivityResource() {
        return activityResource;
    }

    public void setActivityResource(ActivityResource[] activityResource) {
        this.activityResource = activityResource;
    }

    public Teacher toEntity() {
        return new Teacher(
                this.id,
                this.fio_teacher,
                this.id_dolzh,
                this.id_kafedra
        );
    }
}
