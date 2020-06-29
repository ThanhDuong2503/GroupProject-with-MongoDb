package de.neuefische.projectplanning.service;
<<<<<<< HEAD
import de.neuefische.projectplanning.db.IdeaDb;
=======

>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
import de.neuefische.projectplanning.db.IdeaMongoDb;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdeaService {
<<<<<<< HEAD
    private final IdeaMongoDb ideaMongoDb;
    private final IdUtils idUtils;

    @Autowired
    public IdeaService(IdeaMongoDb ideaMongoDb, IdUtils idUtils) {
        this.ideaMongoDb = ideaMongoDb;
=======
    private final IdeaMongoDb ideaDb;
    private final IdUtils idUtils;

    @Autowired
    public IdeaService(IdeaMongoDb ideaDb, IdUtils idUtils) {
        this.ideaDb = ideaDb;
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
        this.idUtils = idUtils;
    }

    public Iterable<Idea> getAll(){
<<<<<<< HEAD
        return ideaMongoDb.findAll();
=======
        return ideaDb.findAll();
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
    }

    public Idea add(String description) {
        Idea idea = new Idea();
        idea.setId(idUtils.generateRandomId());
        idea.setDescription(description);
<<<<<<< HEAD
        return ideaMongoDb.save(idea);
=======
        return ideaDb.save(idea);
    }

    public void deleteIdea(String id) {
        ideaDb.deleteById(id);
    }

    public Optional<Idea> getIdea(String id) {
        return ideaDb.findById(id);
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
    }
}
