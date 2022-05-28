package com.example.kpo.controller;


import com.example.kpo.entity.Dolzh;
import com.example.kpo.repository.DolzhRepository;
import com.example.kpo.repository.TeacherRepository;
import com.example.kpo.resource.DolzhResource;
import com.example.kpo.resource.TeacherResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/dolzh")
public class DolzhController {
    private final DolzhRepository dolzhRepository;
    private final TeacherRepository teacherRepository;

    public DolzhController(DolzhRepository dolzhRepository, TeacherRepository teacherRepository) {
        this.dolzhRepository = dolzhRepository;
        this.teacherRepository = teacherRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    DolzhResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(dolzhRepository.select())
                .map(entity -> {
                    DolzhResource resource = new DolzhResource(entity);
                    if (expand != null)
                        resource.setTeacherResource(
                                Arrays.stream(teacherRepository.selectByDolzhId(entity.getId()))
                                        .map(e -> new TeacherResource(e))
                                        .toArray(TeacherResource[]::new)
                        );
                    return resource;
                })
                .toArray(DolzhResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    DolzhResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Dolzh entity = dolzhRepository.select(id);
        if (entity == null) return null;
        DolzhResource resource = new DolzhResource(entity);
        if (expand != null)
            resource.setTeacherResource(
                    Arrays.stream(teacherRepository.selectByKafedraId(entity.getId()))
                            .map(e -> new TeacherResource(e))
                            .toArray(TeacherResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    DolzhResource post(@RequestBody DolzhResource resource) {
        Dolzh entity = dolzhRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new DolzhResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    DolzhResource put(@PathVariable Integer id,
                        @RequestBody DolzhResource resource) {
        Dolzh entity = dolzhRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new DolzhResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    DolzhResource delete(@PathVariable Integer id) {
        Dolzh entity = dolzhRepository.delete(id);
        if (entity == null) return null;
        return new DolzhResource(entity);
    }
}


