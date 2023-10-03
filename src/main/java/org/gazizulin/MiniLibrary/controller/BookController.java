package org.gazizulin.MiniLibrary.controller;


import jakarta.validation.Valid;
import org.gazizulin.MiniLibrary.model.Book;
import org.gazizulin.MiniLibrary.model.Person;
import org.gazizulin.MiniLibrary.services.BookService;
import org.gazizulin.MiniLibrary.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {

    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public BookController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }


    @GetMapping("")
    public String mainPage(@RequestParam(required = false, name = "page") Integer page,
                                @RequestParam(required = false, name = "books_per_page") Integer books_per_page,
                           @RequestParam(required = false, name = "sort_by_year") boolean sortByYear, Model model){

        if (page == null && books_per_page == null){
            model.addAttribute("books", bookService.findAll(sortByYear));
        } else{
            model.addAttribute("books", bookService.findPaginate(page, books_per_page, sortByYear));
        }


        return "books/index";
    }




    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book, Model model){
        model.addAttribute("book", book);

        return "books/new";
    }

    @PostMapping("")
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@ModelAttribute("person") Person person, @PathVariable int id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        if (book.getOwner() == null){
            model.addAttribute("people", personService.findAll());
        } else{
            model.addAttribute("person", book.getOwner());
        }

        return "books/show";
    }

    @GetMapping("/search")
    public String searchPage(Model model){

        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(@RequestParam("query") String query, Model model){
        System.out.println(query);

        if (query == null){
            model.addAttribute("books", bookService.findAll(false));
        } else{
            model.addAttribute("books", bookService.findByPrefix(query));
        }

        return "books/search";
    }


    @PatchMapping("/{id}/makeFree")
    public String makeBookFree(@PathVariable int id){
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/order")
    public String order(@ModelAttribute("person") Person person, @PathVariable int id){
        bookService.assign(id, person);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable int id, Model model){
        model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book newBook, BindingResult bindingResult
            , @PathVariable int id){

        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookService.update(id, newBook);
        return "redirect:/books/" + id;
    }

}
