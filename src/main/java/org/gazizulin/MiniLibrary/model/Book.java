package org.gazizulin.MiniLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="book")
public class Book {


    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;




    @Column(name = "name")
    @NotEmpty(message = "Name must be not empty")
    @Size(min=2, max=100, message = "Book name size must be between 2 and 100 characters")
    private String name;


    @Column(name = "author")
    @NotEmpty(message = "Author must be not empty")
    @Size(min=2, max=100, message = "Author name size must be between 2 and 100 characters")
    private String author;

    @Column(name = "year_of_publication")
    @Max(value = 2023, message = "Year of publication must be less than 2024")
    @Min(value = 800, message = "Year of publicaotion must be greater than 800")
    private int yearOfPublication;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;


    public Book(){}

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book(int bookId, String name, String author, int yearOfPublication) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }


}
