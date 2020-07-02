package de.neuefische.projectplanning.db;

import de.neuefische.projectplanning.model.PlanningUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDb extends PagingAndSortingRepository<PlanningUser,String> {
}
