package de.neuefische.projectplanning.service;

import de.neuefische.projectplanning.db.IdeaDb;
import de.neuefische.projectplanning.model.Idea;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IdeaService {
    private IdeaDb ideaDb;

    public List<Idea> getAll(){
        return ideaDb.getAll();
    }

    public Idea add(Idea idea) {
        return ideaDb.add(idea);
    }
}
