package com.example.kpo.controller;


import com.example.kpo.entity.Teacher;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.DolzhRepository;
import com.example.kpo.repository.KafedraRepository;
import com.example.kpo.repository.TeacherRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.DolzhResource;
import com.example.kpo.resource.KafedraResource;
import com.example.kpo.resource.TeacherResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    private final DolzhRepository dolzhRepository;
    private final KafedraRepository kafedraRepository;
    private final ActivityRepository activityRepository;


    public TeacherController(TeacherRepository itemRepository, DolzhRepository dolzhRepository, KafedraRepository kafedraRepository, ActivityRepository activityRepository) {
        this.teacherRepository = itemRepository;
        this.dolzhRepository = dolzhRepository;
        this.kafedraRepository = kafedraRepository;
        this.activityRepository = activityRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TeacherResource[] getAll(@RequestParam(required = false) Integer id_dolzh,
                             @RequestParam(required = false) Integer id_kafedra,
                             @RequestParam(required = false) Object expand) {
        /*Class[] entities = id_discipline == null ?
                itemRepository.select() :
                itemRepository.selectByDisciplineId(id_discipline);*/
        Teacher[] entities;
        if(id_dolzh != null){
            entities = teacherRepository.selectByDolzhId(id_dolzh);
        }else if (id_kafedra != null){
            entities = teacherRepository.selectByKafedraId(id_kafedra);
        }else{
            entities = teacherRepository.select();
        }
        return Arrays.stream(entities)
                .map(entity -> {
                    TeacherResource resource = new TeacherResource(entity);
                    if (expand != null){
                        resource.setDolzhResource(new DolzhResource(
                                dolzhRepository.select(entity.getId_dolzh()))
                        );
                        resource.setKafedraResource(new KafedraResource(
                                kafedraRepository.select(entity.getId_kafedra()))
                        );
                        resource.setActivityResource(
                                Arrays.stream(activityRepository.selectByTeacherId(entity.getId()))
                                        .map(e -> new ActivityResource(e))
                                        .toArray(ActivityResource[]::new)
                        );
                    }
                    return resource;
                })
                .toArray(TeacherResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TeacherResource get(@PathVariable Integer id,
                       @RequestParam(required = false) Object expand) {
        Teacher entity = teacherRepository.select(id);
        if (entity == null) return null;
        TeacherResource resource = new TeacherResource(entity);
        if (expand != null){
            resource.setDolzhResource(new DolzhResource(
                    dolzhRepository.select(entity.getId_dolzh()))
            );
            resource.setKafedraResource(new KafedraResource(
                kafedraRepository.select(entity.getId_kafedra()))
             );
            resource.setActivityResource(
                    Arrays.stream(activityRepository.selectByTeacherId(entity.getId()))
                            .map(e -> new ActivityResource(e))
                            .toArray(ActivityResource[]::new)
            );
        }
        return resource;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    TeacherResource post(@RequestBody TeacherResource resource) {
        Teacher entity = teacherRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new TeacherResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TeacherResource put(@PathVariable Integer id,
                       @RequestBody TeacherResource resource) {
        Teacher entity = teacherRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new TeacherResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TeacherResource delete(@PathVariable Integer id) {
        Teacher entity = teacherRepository.delete(id);
        if (entity == null) return null;
        TeacherResource resource = new TeacherResource(entity);
        return resource;
    }
}
