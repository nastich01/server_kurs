package com.example.kpo.controller;

import com.example.kpo.entity.Activity;
import com.example.kpo.repository.*;
import com.example.kpo.resource.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/activity")
public class ActivityController {
    private final ActivityRepository activityRepository;
    /*private final StudentActivityRepository studentActivityRepository;
    private final TeacherActivityRepository teacherActivityRepository;*/
    private final EventRepository eventRepository;
    private final TipRepository tipRepository;
    private final DirectionRepository directionRepository;
    //
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;




    public ActivityController(ActivityRepository activityRepository,EventRepository eventRepository, TipRepository tipRepository, DirectionRepository directionRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.activityRepository = activityRepository;
        /*this.studentActivityRepository = studentActivityRepository;
        this.teacherActivityRepository = teacherActivityRepository;*/
        this.eventRepository = eventRepository;
        this.tipRepository = tipRepository;
        this.directionRepository = directionRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    ActivityResource[] getAll(@RequestParam(required = false) Integer id_event,
                             @RequestParam(required = false) Integer id_tip,
                             @RequestParam(required = false) Integer id_direction,
                              @RequestParam(required = false) Integer id_student,
                              @RequestParam(required = false) Integer id_teacher,
                             @RequestParam(required = false) Object expand) {
        /*Class[] entities = id_discipline == null ?
                itemRepository.select() :
                itemRepository.selectByDisciplineId(id_discipline);*/
        Activity[] entities;
        if(id_event != null){
            entities = activityRepository.selectByEventId(id_event);
        }else if (id_tip != null){
            entities = activityRepository.selectByTipId(id_tip);
        }else if (id_direction != null){
            entities = activityRepository.selectByDirectionId(id_direction);
        }else if (id_student != null){
            entities = activityRepository.selectByStudentId(id_student);
        }else if (id_teacher != null){
            entities = activityRepository.selectByTeacherId(id_teacher);
        }else{
            entities = activityRepository.select();
        }
        return Arrays.stream(entities)
                .map(entity -> {
                    ActivityResource resource = new ActivityResource(entity);
                    if (expand != null){
                        /*resource.setStudentActivityResource(
                                Arrays.stream(studentActivityRepository.selectByActivityId(entity.getId()))
                                        .map(e -> new StudentActivityResource(e))
                                        .toArray(StudentActivityResource[]::new)
                        );*/
                        /*resource.setTeacherActivityResource(
                                Arrays.stream(teacherActivityRepository.selectByActivityId(entity.getId()))
                                        .map(e -> new TeacherActivityResource(e))
                                        .toArray(TeacherActivityResource[]::new)
                        );*/
                        resource.setEventResource(new EventResource(
                                eventRepository.select(entity.getId_event()))
                        );
                        resource.setTipResource(new TipResource(
                                tipRepository.select(entity.getId_tip()))
                        );
                        resource.setDirectionResource(new DirectionResource(
                                directionRepository.select(entity.getId_direction()))
                        );
                        resource.setStudentResource(new StudentResource(
                                studentRepository.select(entity.getId_student()))
                        );
                        resource.setTeacherResource(new TeacherResource(
                                teacherRepository.select(entity.getId_teacher()))
                        );
                    }
                    return resource;
                })
                .toArray(ActivityResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ActivityResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Activity entity = activityRepository.select(id);
        if (entity == null) return null;
        ActivityResource resource = new ActivityResource(entity);
        if (expand != null) {
            resource.setEventResource(new EventResource(
                    eventRepository.select(entity.getId_event()))
            );
            resource.setTipResource(new TipResource(
                    tipRepository.select(entity.getId_tip()))
            );
            resource.setDirectionResource(new DirectionResource(
                    directionRepository.select(entity.getId_direction()))
            );
            resource.setStudentResource(new StudentResource(
                    studentRepository.select(entity.getId_student()))
            );
            resource.setTeacherResource(new TeacherResource(
                    teacherRepository.select(entity.getId_teacher()))
            );
            /*resource.setStudentActivityResource(
                    Arrays.stream(studentActivityRepository.selectByActivityId(entity.getId()))
                            .map(e -> new StudentActivityResource(e))
                            .toArray(StudentActivityResource[]::new)
            );
            resource.setTeacherActivityResource(
                    Arrays.stream(teacherActivityRepository.selectByActivityId(entity.getId()))
                            .map(e -> new TeacherActivityResource(e))
                            .toArray(TeacherActivityResource[]::new)
            );*/
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ActivityResource post(@RequestBody ActivityResource resource) {
        Activity entity = activityRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new ActivityResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ActivityResource put(@PathVariable Integer id,
                      @RequestBody ActivityResource resource) {
        Activity entity = activityRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new ActivityResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ActivityResource delete(@PathVariable Integer id) {
        Activity entity = activityRepository.delete(id);
        if (entity == null) return null;
        return new ActivityResource(entity);
    }
}




