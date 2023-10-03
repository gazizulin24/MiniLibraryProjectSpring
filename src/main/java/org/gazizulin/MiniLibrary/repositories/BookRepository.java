package org.gazizulin.MiniLibrary.repositories;

import org.gazizulin.MiniLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timur Gazizulin
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
