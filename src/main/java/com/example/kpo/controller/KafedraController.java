package com.example.kpo.controller;



import com.example.kpo.entity.Kafedra;
import com.example.kpo.repository.KafedraRepository;
import com.example.kpo.repository.TeacherRepository;
import com.example.kpo.resource.KafedraResource;
import com.example.kpo.resource.TeacherResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/kafedra")
public class KafedraController {
    private final KafedraRepository kafedraRepository;
    private final TeacherRepository teacherRepository;

    public KafedraController(KafedraRepository kafedraRepository, TeacherRepository teacherRepository) {
        this.kafedraRepository = kafedraRepository;
        this.teacherRepository = teacherRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    KafedraResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(kafedraRepository.select())
                .map(entity -> {
                    KafedraResource resource = new KafedraResource(entity);
                    if (expand != null)
                        resource.setTeacherResource(
                                Arrays.stream(teacherRepository.selectByKafedraId(entity.getId()))
                                        .map(e -> new TeacherResource(e))
                                        .toArray(TeacherResource[]::new)
                        );
                    return resource;
                })
                .toArray(KafedraResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    KafedraResource get(@PathVariable Integer id,
                    @RequestParam(required = false) Object expand) {
        Kafedra entity = kafedraRepository.select(id);
        if (entity == null) return null;
        KafedraResource resource = new KafedraResource(entity);
        if (expand != null)
            resource.setTeacherResource(
                    Arrays.stream(teacherRepository.selectByKafedraId(entity.getId()))
                            .map(e -> new TeacherResource(e))
                            .toArray(TeacherResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    KafedraResource post(@RequestBody KafedraResource resource) {
        Kafedra entity = kafedraRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new KafedraResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    KafedraResource put(@PathVariable Integer id,
                    @RequestBody KafedraResource resource) {
        Kafedra entity = kafedraRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new KafedraResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    KafedraResource delete(@PathVariable Integer id) {
        Kafedra entity = kafedraRepository.delete(id);
        if (entity == null) return null;
        return new KafedraResource(entity);
    }
}

