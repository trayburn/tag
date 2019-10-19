package org.improving.tag.database;

import java.util.List;

import org.improving.tag.Exit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitRepository extends CrudRepository<Exit, Long> {
	
	List<Exit> findAll();
}
