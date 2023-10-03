package org.gazizulin.MiniLibrary.repositories;

import org.gazizulin.MiniLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Timur Gazizulin
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findBooksByNameStartingWith(String query);
}
