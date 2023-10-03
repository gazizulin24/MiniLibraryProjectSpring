package org.gazizulin.MiniLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name="person")
public class Person {

    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;


    @Column(name="name")
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 2, max = 100, message = "Name size must be between 2 and 100 characters")
    private String name;


    @Column(name="year_of_birth")
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;


    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(){}

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Person(int personId, String name, int yearOfBirth) {
        this.personId = personId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int dateOfBirth) {
        this.yearOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
