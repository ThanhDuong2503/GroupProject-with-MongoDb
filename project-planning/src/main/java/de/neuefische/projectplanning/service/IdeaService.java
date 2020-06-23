package de.neuefische.projectplanning.service;

import de.neuefische.projectplanning.db.IdeaDb;
import de.neuefische.projectplanning.model.Idea;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IdeaService {
    private IdeaDb ideaDb;

    public List<Idea> getAll(){
        return ideaDb.getAll();
    }

    public Idea add(String description) {
        Idea idea = new Idea();
        idea.setId(UUID.randomUUID().toString());
        idea.setDescription(description);
        return ideaDb.add(idea);
    }
}
