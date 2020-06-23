package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.AddIdeaDto;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public List<Idea> getIdeas() {
        return ideaService.getAll();
    }

    @PutMapping
    public Idea addToDo(@RequestBody @Valid AddIdeaDto data){
        return ideaService.add(data.getDescription());
    }

}
