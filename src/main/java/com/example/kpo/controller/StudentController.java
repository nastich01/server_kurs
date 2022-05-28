package com.example.kpo.controller;



import com.example.kpo.entity.Student;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.FacultyRepository;
import com.example.kpo.repository.StudentActivityRepository;
import com.example.kpo.repository.StudentRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.FacultyResource;
import com.example.kpo.resource.StudentActivityResource;
import com.example.kpo.resource.StudentResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final ActivityRepository activityRepository;

    //private final StudentActivityRepository studentActivityRepository;


    public StudentController(StudentRepository itemRepository, FacultyRepository facultyRepository, ActivityRepository activityRepository) {
        this.studentRepository = itemRepository;
        this.facultyRepository = facultyRepository;
        this.activityRepository = activityRepository;
        //this.studentActivityRepository = studentActivityRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    StudentResource[] getAll(@RequestParam(required = false) Integer kurs,
                             @RequestParam(required = false) String group,
                             @RequestParam(required = false) Integer id_faculty,
                             @RequestParam(required = false) Object expand) {
        /*Class[] entities = id_discipline == null ?
                itemRepository.select() :
                itemRepository.selectByDisciplineId(id_discipline);*/
        Student[] entities;
        if(kurs != null){
            entities = studentRepository.selectByKurs(kurs);
        }else if (group != null){
            entities = studentRepository.selectByGroup(group);
        }else if (id_faculty != null){
            entities = studentRepository.selectByFacultyId(id_faculty);
        }else{
            entities = studentRepository.select();
        }
        return Arrays.stream(entities)
                .map(entity -> {
                    StudentResource resource = new StudentResource(entity);
                    if (expand != null){
                        resource.setFacultyResource(new FacultyResource(
                                facultyRepository.select(entity.getId_faculty()))
                        );
                        resource.setActivityResource(
                                Arrays.stream(activityRepository.selectByStudentId(entity.getId()))
                                        .map(e -> new ActivityResource(e))
                                        .toArray(ActivityResource[]::new)
                        );
                        /*resource.setStudentActivityResource(
                                Arrays.stream(studentActivityRepository.selectByStudentId(entity.getId()))
                                        .map(e -> new StudentActivityResource(e))
                                        .toArray(StudentActivityResource[]::new)
                        );*/
                    }
                    return resource;
                })
                .toArray(StudentResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    StudentResource get(@PathVariable Integer id,
                       @RequestParam(required = false) Object expand) {
        Student entity = studentRepository.select(id);
        if (entity == null) return null;
        StudentResource resource = new StudentResource(entity);
        if (expand != null){
            resource.setFacultyResource(
                    new FacultyResource(facultyRepository.select(entity.getId_faculty()))
            );
            /*resource.setStudentActivityResource(
                    Arrays.stream(studentActivityRepository.selectByActivityId(entity.getId()))
                            .map(e -> new StudentActivityResource(e))
                            .toArray(StudentActivityResource[]::new)
            );*/
            resource.setActivityResource(
                    Arrays.stream(activityRepository.selectByStudentId(entity.getId()))
                            .map(e -> new ActivityResource(e))
                            .toArray(ActivityResource[]::new)
            );
        }
        return resource;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    StudentResource post(@RequestBody StudentResource resource) {
        Student entity = studentRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new StudentResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    StudentResource put(@PathVariable Integer id,
                       @RequestBody StudentResource resource) {
        Student entity = studentRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new StudentResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    StudentResource delete(@PathVariable Integer id) {
        Student entity = studentRepository.delete(id);
        if (entity == null) return null;
        StudentResource resource = new StudentResource(entity);
        return resource;
    }
}

