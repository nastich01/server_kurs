package com.example.kpo.controller;

import com.example.kpo.entity.TeacherActivity;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.TeacherActivityRepository;
import com.example.kpo.repository.TeacherRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.TeacherActivityResource;
import com.example.kpo.resource.TeacherResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/teacher_activity")
public class TeacherActivityController {
    private final TeacherActivityRepository teacherActivityRepository;

    private final ActivityRepository activityRepository;
    private final TeacherRepository teacherRepository;

    public TeacherActivityController(TeacherActivityRepository itemRepository, ActivityRepository activityRepository, TeacherRepository teacherRepository) {
        this.teacherActivityRepository = itemRepository;
        this.activityRepository = activityRepository;
        this.teacherRepository = teacherRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TeacherActivityResource[] getAll(@RequestParam(required = false) Integer id_activity,
                                     @RequestParam(required = false) Integer id_teacher,
                                     @RequestParam(required = false) Object expand) {
        /*Class[] entities = id_discipline == null ?
                itemRepository.select() :
                itemRepository.selectByDisciplineId(id_discipline);*/
        TeacherActivity[] entities;
        if(id_activity != null){
            entities = teacherActivityRepository.selectByActivityId(id_activity);
        }else if (id_teacher != null){
            entities = teacherActivityRepository.selectByTeacherId(id_teacher);
        }else{
            entities = teacherActivityRepository.select();
        }
        return Arrays.stream(entities)
                .map(entity -> {
                    TeacherActivityResource resource = new TeacherActivityResource(entity);
                    if (expand != null){
                        resource.setActivityResource(new ActivityResource(
                                activityRepository.select(entity.getId_activity()))
                        );
                        resource.setTeacherResource(new TeacherResource(
                                teacherRepository.select(entity.getId_teacher()))
                        );
                    }
                    return resource;
                })
                .toArray(TeacherActivityResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TeacherActivityResource get(@PathVariable Integer id,
                       @RequestParam(required = false) Object expand) {
        TeacherActivity entity = teacherActivityRepository.select(id);
        if (entity == null) return null;
        TeacherActivityResource resource = new TeacherActivityResource(entity);
        if (expand != null)
            resource.setTeacherResource(
                    new TeacherResource(teacherRepository.select(entity.getId_teacher()))
            );
        return resource;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    TeacherActivityResource post(@RequestBody TeacherActivityResource resource) {
        TeacherActivity entity = teacherActivityRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new TeacherActivityResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TeacherActivityResource put(@PathVariable Integer id,
                       @RequestBody TeacherActivityResource resource) {
        TeacherActivity entity = teacherActivityRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new TeacherActivityResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TeacherActivityResource delete(@PathVariable Integer id) {
        TeacherActivity entity = teacherActivityRepository.delete(id);
        if (entity == null) return null;
        TeacherActivityResource resource = new TeacherActivityResource(entity);
        return resource;
    }
}
