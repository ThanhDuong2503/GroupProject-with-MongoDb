package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.Idea;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ideas")
public class IdeaController {

  @GetMapping
  public List<Idea> getIdeas(){
    return List.of(
        new Idea("1","Some Fancy Idea"),
        new Idea("2","Some other Fancy Idea")
    );
  }

}
