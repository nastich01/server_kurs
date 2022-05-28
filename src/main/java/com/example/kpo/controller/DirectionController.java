package com.example.kpo.controller;


import com.example.kpo.entity.Direction;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.DirectionRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.DirectionResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/direction")
public class DirectionController {
    private final DirectionRepository directionRepository;
    private final ActivityRepository activityRepository;

    public DirectionController(DirectionRepository directionRepository, ActivityRepository activityRepository) {
        this.directionRepository = directionRepository;
        this.activityRepository = activityRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    DirectionResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(directionRepository.select())
                .map(entity -> {
                    DirectionResource resource = new DirectionResource(entity);
                    if (expand != null)
                        resource.setActivityResource(
                                Arrays.stream(activityRepository.selectByDirectionId(entity.getId()))
                                        .map(e -> new ActivityResource(e))
                                        .toArray(ActivityResource[]::new)
                        );
                    return resource;
                })
                .toArray(DirectionResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    DirectionResource get(@PathVariable Integer id,
                    @RequestParam(required = false) Object expand) {
        Direction entity = directionRepository.select(id);
        if (entity == null) return null;
        DirectionResource resource = new DirectionResource(entity);
        if (expand != null)
            resource.setActivityResource(
                    Arrays.stream(activityRepository.selectByDirectionId(entity.getId()))
                            .map(e -> new ActivityResource(e))
                            .toArray(ActivityResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    DirectionResource post(@RequestBody DirectionResource resource) {
        Direction entity = directionRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new DirectionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    DirectionResource put(@PathVariable Integer id,
                    @RequestBody DirectionResource resource) {
        Direction entity = directionRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new DirectionResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    DirectionResource delete(@PathVariable Integer id) {
        Direction entity = directionRepository.delete(id);
        if (entity == null) return null;
        return new DirectionResource(entity);
    }
}


