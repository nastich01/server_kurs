package com.example.kpo.resource;

import com.example.kpo.entity.Dolzh;
import com.fasterxml.jackson.annotation.JsonInclude;

public class DolzhResource extends BaseResource{
    private Integer id;
    private String dolzh_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherResource[] teacherResource;

    public DolzhResource() {}

    public DolzhResource(Dolzh dolzh) {
        this.id = dolzh.getId();
        this.dolzh_name = dolzh.getDolzh_name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDolzh_name() {
        return dolzh_name;
    }

    public void setDolzh_name(String dolzh_name) {
        this.dolzh_name = dolzh_name;
    }

    public TeacherResource[] getTeacherResource() {
        return teacherResource;
    }

    public void setTeacherResource(TeacherResource[] teacherResource) {
        this.teacherResource = teacherResource;
    }

    public Dolzh toEntity() {
        return new Dolzh(
                this.id,
                this.dolzh_name
        );
    }
}
