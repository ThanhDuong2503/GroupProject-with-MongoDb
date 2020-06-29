package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.AddIdeaDto;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
<<<<<<< HEAD
    public List<Idea> getIdeas() {
        return (List<Idea>) ideaService.getAll();
=======
    public Iterable<Idea> getIdeas() {
        return ideaService.getAll();
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
    }

    @PutMapping
    public Idea addIdea(@RequestBody @Valid AddIdeaDto data){
        return ideaService.add(data.getDescription());
    }

    @DeleteMapping("{id}")
    public void deleteIdea(@PathVariable String id){
        ideaService.deleteIdea(id);
    }

    @GetMapping("{id}")
    public Idea getIdeaById(@PathVariable String id) {
      Optional<Idea> ideaOptional = ideaService.getIdea(id);
      if (ideaOptional.isPresent()) {
        return ideaOptional.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "idea with " + id + " not exists");
    }
}
