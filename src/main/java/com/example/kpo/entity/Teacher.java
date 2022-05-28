package com.example.kpo.entity;

public class Teacher extends BaseEntity{
    private String fio_teacher;
    private Integer id_dolzh;
    private Integer id_kafedra;

    public Teacher(Integer id, String fio_teacher, Integer id_dolzh, Integer id_kafedra) {
        super(id);
        this.fio_teacher = fio_teacher;
        this.id_dolzh = id_dolzh;
        this.id_kafedra = id_kafedra;
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
}
