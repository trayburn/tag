package org.improving.tag.database;

import java.util.List;

import org.improving.tag.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
	
	List<Location> findAll();
}
