package de.neuefische.projectplanning.service;

import de.neuefische.projectplanning.db.IdeaMongoDb;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdeaService {
    private final IdeaMongoDb ideaDb;
    private final IdUtils idUtils;

    @Autowired
    public IdeaService(IdeaMongoDb ideaDb, IdUtils idUtils) {
        this.ideaDb = ideaDb;
        this.idUtils = idUtils;
    }

    public Iterable<Idea> getAll(){
        return ideaDb.findAll();
    }

    public Idea add(String description, String user) {
        Idea idea = new Idea();
        idea.setId(idUtils.generateRandomId());
        idea.setDescription(description);
        idea.setUser(user);
        return ideaDb.save(idea);
    }

    public void deleteIdea(String id) {
        ideaDb.deleteById(id);
    }

    public Optional<Idea> getIdea(String id) {
        return ideaDb.findById(id);
    }
}
