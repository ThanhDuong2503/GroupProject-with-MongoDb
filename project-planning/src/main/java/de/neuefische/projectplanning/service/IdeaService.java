package de.neuefische.projectplanning.service;
import de.neuefische.projectplanning.db.IdeaDb;
import de.neuefische.projectplanning.db.IdeaMongoDb;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {
    private final IdeaMongoDb ideaMongoDb;
    private final IdUtils idUtils;

    @Autowired
    public IdeaService(IdeaMongoDb ideaMongoDb, IdUtils idUtils) {
        this.ideaMongoDb = ideaMongoDb;
        this.idUtils = idUtils;
    }

    public Iterable<Idea> getAll(){
        return ideaMongoDb.findAll();
    }

    public Idea add(String description) {
        Idea idea = new Idea();
        idea.setId(idUtils.generateRandomId());
        idea.setDescription(description);
        return ideaMongoDb.save(idea);
    }
}
