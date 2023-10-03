package org.gazizulin.MiniLibrary.services;

import org.gazizulin.MiniLibrary.controller.BookController;
import org.gazizulin.MiniLibrary.controller.PersonController;
import org.gazizulin.MiniLibrary.model.Book;
import org.gazizulin.MiniLibrary.model.Person;
import org.gazizulin.MiniLibrary.repositories.BookRepository;
import org.gazizulin.MiniLibrary.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Service
@Transactional(readOnly = true)
public class BookService {


    private final BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setBookId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }


    @Transactional
    public void assign(int id, Person person){
        bookRepository.findById(id).ifPresent(book -> book.setOwner(person));
    }

    @Transactional
    public void release(int id){
        bookRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }


}
