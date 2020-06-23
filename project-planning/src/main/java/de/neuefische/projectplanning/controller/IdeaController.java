package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.CompositeData;
import java.util.List;

@RestController
@RequestMapping("api/ideas")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping
    public List<Idea> getIdeas() {
        return ideaService.getAll();
    }

    @PutMapping
    public Idea addToDo(@RequestBody String description){
        return ideaService.add(description);
    }

}
