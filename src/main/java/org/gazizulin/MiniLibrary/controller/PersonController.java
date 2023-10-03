package org.gazizulin.MiniLibrary.controller;


import jakarta.validation.Valid;
import org.gazizulin.MiniLibrary.model.Person;
import org.gazizulin.MiniLibrary.services.BookService;
import org.gazizulin.MiniLibrary.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public PersonController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }


    @GetMapping("/new")
    public String newPersonPage(@ModelAttribute("person") Person person, Model model){
        model.addAttribute("person", person);

        return "people/new";
    }

    @PostMapping("")
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personService.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}")
    public String personPage(@PathVariable int id, Model model){
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", personService.findBooksByPersonId(id));

        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id){
        personService.delete(id);

        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable int id){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personService.edit(id, person);

        return "redirect:/people/" + id;

    }


}
