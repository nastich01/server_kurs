package com.example.kpo.controller;


import com.example.kpo.entity.Tip;
import com.example.kpo.repository.ActivityRepository;
import com.example.kpo.repository.TipRepository;
import com.example.kpo.resource.ActivityResource;
import com.example.kpo.resource.TipResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/tip")
public class TipController {
    private final TipRepository tipRepository;
    private final ActivityRepository activityRepository;

    public TipController(TipRepository tipRepository, ActivityRepository activityRepository) {
        this.tipRepository = tipRepository;
        this.activityRepository = activityRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TipResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(tipRepository.select())
                .map(entity -> {
                    TipResource resource = new TipResource(entity);
                    if (expand != null)
                        resource.setActivityResource(
                                Arrays.stream(activityRepository.selectByTipId(entity.getId()))
                                        .map(e -> new ActivityResource(e))
                                        .toArray(ActivityResource[]::new)
                        );
                    return resource;
                })
                .toArray(TipResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TipResource get(@PathVariable Integer id,
                           @RequestParam(required = false) Object expand) {
        Tip entity = tipRepository.select(id);
        if (entity == null) return null;
        TipResource resource = new TipResource(entity);
        if (expand != null)
            resource.setActivityResource(
                    Arrays.stream(activityRepository.selectByTipId(entity.getId()))
                            .map(e -> new ActivityResource(e))
                            .toArray(ActivityResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    TipResource post(@RequestBody TipResource resource) {
        Tip entity = tipRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new TipResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TipResource put(@PathVariable Integer id,
                           @RequestBody TipResource resource) {
        Tip entity = tipRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new TipResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TipResource delete(@PathVariable Integer id) {
        Tip entity = tipRepository.delete(id);
        if (entity == null) return null;
        return new TipResource(entity);
    }
}
