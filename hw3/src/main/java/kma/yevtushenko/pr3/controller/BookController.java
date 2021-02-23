package kma.yevtushenko.pr3.controller;

import kma.yevtushenko.pr3.entity.Book;
import kma.yevtushenko.pr3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String loadMainPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "mainPage";
    }

    @GetMapping("/add")
    public String addBookGet(Model model) {
        model.addAttribute("newBook", new Book());
        return "book-add";
    }

    @PostMapping("/add")
    public String addBookPost(
            @ModelAttribute("newBook") Book newBook, Model model) {
        model.addAttribute("newBook", newBook); // ????
        this.bookService.addNewBook(newBook);
        return "redirect:/main";
    }


}
