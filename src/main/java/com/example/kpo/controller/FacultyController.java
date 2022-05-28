package com.example.kpo.controller;



import com.example.kpo.entity.Faculty;
import com.example.kpo.repository.FacultyRepository;

import com.example.kpo.repository.StudentRepository;
import com.example.kpo.resource.FacultyResource;
import com.example.kpo.resource.StudentResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/faculty")
public class FacultyController {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyController(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    FacultyResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(facultyRepository.select())
                .map(entity -> {
                    FacultyResource resource = new FacultyResource(entity);
                    if (expand != null)
                        resource.setStudentResource(
                                Arrays.stream(studentRepository.selectByFacultyId(entity.getId()))
                                        .map(e -> new StudentResource(e))
                                        .toArray(StudentResource[]::new)
                        );
                    return resource;
                })
                .toArray(FacultyResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    FacultyResource get(@PathVariable Integer id,
                    @RequestParam(required = false) Object expand) {
        Faculty entity = facultyRepository.select(id);
        if (entity == null) return null;
        FacultyResource resource = new FacultyResource(entity);
        if (expand != null)
            resource.setStudentResource(
                    Arrays.stream(studentRepository.selectByFacultyId(entity.getId()))
                            .map(e -> new StudentResource(e))
                            .toArray(StudentResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    FacultyResource post(@RequestBody FacultyResource resource) {
        Faculty entity = facultyRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new FacultyResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    FacultyResource put(@PathVariable Integer id,
                    @RequestBody FacultyResource resource) {
        Faculty entity = facultyRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new FacultyResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    FacultyResource delete(@PathVariable Integer id) {
        Faculty entity = facultyRepository.delete(id);
        if (entity == null) return null;
        return new FacultyResource(entity);
    }
}
