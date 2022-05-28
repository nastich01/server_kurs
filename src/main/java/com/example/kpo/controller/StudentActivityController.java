package com.example.kpo.controller;

import com.example.kpo.entity.StudentActivity;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.StudentActivityRepository;
import com.example.kpo.repository.StudentRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.StudentActivityResource;
import com.example.kpo.resource.StudentResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/student_activity")
public class StudentActivityController {
    private final StudentActivityRepository studentActivityRepository;

    private final ActivityRepository activityRepository;
    private final StudentRepository studentRepository;

    public StudentActivityController(StudentActivityRepository itemRepository, ActivityRepository activityRepository, StudentRepository studentRepository) {
        this.studentActivityRepository = itemRepository;
        this.activityRepository = activityRepository;
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    StudentActivityResource[] getAll(@RequestParam(required = false) Integer id_activity,
                                     @RequestParam(required = false) Integer id_student,
                                     @RequestParam(required = false) Object expand) {
        /*Class[] entities = id_discipline == null ?
                itemRepository.select() :
                itemRepository.selectByDisciplineId(id_discipline);*/
        StudentActivity[] entities;
        if(id_activity != null){
            entities = studentActivityRepository.selectByActivityId(id_activity);
        }else if (id_student != null){
            entities = studentActivityRepository.selectByStudentId(id_student);
        }else{
            entities = studentActivityRepository.select();
        }
        return Arrays.stream(entities)
                .map(entity -> {
                    StudentActivityResource resource = new StudentActivityResource(entity);
                    if (expand != null){
                        resource.setActivityResource(new ActivityResource(
                                activityRepository.select(entity.getId_activity()))
                        );
                        resource.setStudentResource(new StudentResource(
                                studentRepository.select(entity.getId_student()))
                        );
                    }
                    return resource;
                })
                .toArray(StudentActivityResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    StudentActivityResource get(@PathVariable Integer id,
                                @RequestParam(required = false) Object expand) {
        StudentActivity entity = studentActivityRepository.select(id);
        if (entity == null) return null;
        StudentActivityResource resource = new StudentActivityResource(entity);
        if (expand != null)
            resource.setStudentResource(
                    new StudentResource(studentRepository.select(entity.getId_student()))
            );
        return resource;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    StudentActivityResource post(@RequestBody StudentActivityResource resource) {
        StudentActivity entity = studentActivityRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new StudentActivityResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    StudentActivityResource put(@PathVariable Integer id,
                                @RequestBody StudentActivityResource resource) {
        StudentActivity entity = studentActivityRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new StudentActivityResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    StudentActivityResource delete(@PathVariable Integer id) {
        StudentActivity entity = studentActivityRepository.delete(id);
        if (entity == null) return null;
        StudentActivityResource resource = new StudentActivityResource(entity);
        return resource;
    }
}
