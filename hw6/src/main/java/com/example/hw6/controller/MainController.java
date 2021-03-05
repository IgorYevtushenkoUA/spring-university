package com.example.hw6.controller;

import com.example.hw6.entity.AuthorEntity;
import com.example.hw6.service.AuthorHasBookService;
import com.example.hw6.service.AuthorService;
import com.example.hw6.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorHasBookService authorHasBookService;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("authors", authorService.getAllAuthor());
        return "main";
    }

    @GetMapping("/authors")
    public String authors(Model model) {

        model.addAttribute("authors", authorService.getAllAuthor());
        return "authors";
    }

    @GetMapping("/books")
    public String books(Model model) {

        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/books/{id}")
    public String book(Model model, @PathVariable int id) {

        System.out.println(bookService.getAllBooks());
        System.out.println(authorHasBookService.getAuthorsByBookId(id));
        model.addAttribute("authors", authorHasBookService.getAuthorsByBookId(id));
        model.addAttribute("book", bookService.getBookById(id));
        return "book";
    }


}
