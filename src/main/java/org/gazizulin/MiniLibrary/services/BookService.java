package org.gazizulin.MiniLibrary.services;

import org.gazizulin.MiniLibrary.controller.BookController;
import org.gazizulin.MiniLibrary.controller.PersonController;
import org.gazizulin.MiniLibrary.model.Book;
import org.gazizulin.MiniLibrary.model.Person;
import org.gazizulin.MiniLibrary.repositories.BookRepository;
import org.gazizulin.MiniLibrary.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    public List<Book> findAll(boolean sortByYear){
        if(sortByYear){
            return bookRepository.findAll(Sort.by("yearOfPublication"));
        } else{
            return bookRepository.findAll();
        }
    }

    public Book findById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findPaginate(int page, int books_per_page, boolean sortByYear){
        if (sortByYear){
            return bookRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("yearOfPublication"))).getContent();
        } else{
            return bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
        }
    }



    public List<Book> findByPrefix(String query){
        return bookRepository.findBooksByNameStartingWith(query);
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
        bookRepository.findById(id).ifPresent(book -> {book.setOwner(person); book.setTakenAt(new Date());});
    }

    @Transactional
    public void release(int id){
        bookRepository.findById(id).ifPresent(book -> {book.setOwner(null); book.setTakenAt(null);});
    }


}
