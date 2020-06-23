package de.neuefische.projectplanning.db;

import de.neuefische.projectplanning.model.Idea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class IdeaDb {
    private final ArrayList<Idea> ideas = new ArrayList<>(List.of());

    public List<Idea> getAll() {
        return Collections.unmodifiableList(ideas);
    }

    public Idea add(Idea idea) {
        ideas.add(idea);
        return idea;
    }

    public void clearDb() {
        ideas.clear();
    }
}
