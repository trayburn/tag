package org.improving.tag.database;

import java.util.List;

import org.improving.tag.Adversary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdversaryRepository extends CrudRepository<Adversary, Long> {
	
	List<Adversary> findAll();
}
