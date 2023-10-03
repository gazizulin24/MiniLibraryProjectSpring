package org.gazizulin.MiniLibrary.repositories;

import org.gazizulin.MiniLibrary.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timur Gazizulin
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
