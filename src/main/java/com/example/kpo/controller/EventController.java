package com.example.kpo.controller;

import com.example.kpo.entity.Event;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.EventRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.EventResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/event")
public class EventController {
    private final EventRepository eventRepository;
    private final ActivityRepository activityRepository;

    public EventController(EventRepository eventRepository, ActivityRepository activityRepository) {
        this.eventRepository = eventRepository;
        this.activityRepository = activityRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    EventResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(eventRepository.select())
                .map(entity -> {
                    EventResource resource = new EventResource(entity);
                    if (expand != null)
                        resource.setActivityResource(
                                Arrays.stream(activityRepository.selectByEventId(entity.getId()))
                                        .map(e -> new ActivityResource(e))
                                        .toArray(ActivityResource[]::new)
                        );
                    return resource;
                })
                .toArray(EventResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    EventResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Event entity = eventRepository.select(id);
        if (entity == null) return null;
        EventResource resource = new EventResource(entity);
        if (expand != null)
            resource.setActivityResource(
                    Arrays.stream(activityRepository.selectByEventId(entity.getId()))
                            .map(e -> new ActivityResource(e))
                            .toArray(ActivityResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    EventResource post(@RequestBody EventResource resource) {
        Event entity = eventRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new EventResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    EventResource put(@PathVariable Integer id,
                          @RequestBody EventResource resource) {
        Event entity = eventRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new EventResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    EventResource delete(@PathVariable Integer id) {
        Event entity = eventRepository.delete(id);
        if (entity == null) return null;
        return new EventResource(entity);
    }
}



