package com.example.kpo.resource;

import com.example.kpo.entity.Kafedra;
import com.fasterxml.jackson.annotation.JsonInclude;

public class KafedraResource extends BaseResource{
    private Integer id;
    private String kafedra_name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeacherResource[] teacherResource;

    public KafedraResource() {}

    public KafedraResource(Kafedra kafedra) {
        this.id = kafedra.getId();
        this.kafedra_name = kafedra.getKafedra_name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKafedra_name() {
        return kafedra_name;
    }

    public void setKafedra_name(String kafedra_name) {
        this.kafedra_name = kafedra_name;
    }

    public TeacherResource[] getTeacherResource() {
        return teacherResource;
    }

    public void setTeacherResource(TeacherResource[] teacherResource) {
        this.teacherResource = teacherResource;
    }

    public Kafedra toEntity() {
        return new Kafedra(
                this.id,
                this.kafedra_name
        );
    }
}
