package org.gazizulin.MiniLibrary.services;

import org.gazizulin.MiniLibrary.model.Book;
import org.gazizulin.MiniLibrary.model.Person;
import org.gazizulin.MiniLibrary.repositories.BookRepository;
import org.gazizulin.MiniLibrary.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(int id){
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }


    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }


    public List<Book> findBooksByPersonId(int id){
        Person person = personRepository.findById(id).orElse(null);
        Hibernate.initialize(person.getBooks());
        return person.getBooks();
    }

    @Transactional
    public void edit(int id, Person updatedPerson){
        updatedPerson.setPersonId(id);
        personRepository.save(updatedPerson);
    }


}
