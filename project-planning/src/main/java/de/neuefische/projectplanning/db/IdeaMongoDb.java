package de.neuefische.projectplanning.db;

import de.neuefische.projectplanning.model.Idea;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IdeaMongoDb extends PagingAndSortingRepository<Idea,String> {
}
