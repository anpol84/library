package org.example.controllers;

import org.example.dao.BookDao;
import org.example.dao.PersonDao;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PersonDao personDao;
    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        Book book = bookDao.show(id).get();
        model.addAttribute("book", book);
        model.addAttribute("people", personDao.index());
        if (book.getPerson_id() != null){
            model.addAttribute("concrete_people", personDao.show(book.getPerson_id()).get());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id).get());
        return "books/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("book")  @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDao.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/set")
    public String set(@PathVariable("id") int bookId, @ModelAttribute("person")Person person){
        bookDao.set(person.getPerson_id(), bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/unset")
    public String unset(@PathVariable("id") int bookId){
        bookDao.unset(bookId);
        return "redirect:/books";
    }

}
