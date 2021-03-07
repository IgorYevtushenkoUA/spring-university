package com.example.hw6.controller;

import com.example.hw6.entity.AuthorEntity;
import com.example.hw6.entity.BookEntity;
import com.example.hw6.model.Book;
import com.example.hw6.service.AuthorHasBookService;
import com.example.hw6.service.AuthorService;
import com.example.hw6.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String authorsGet(Model model) {

        model.addAttribute("authorName", new String("have name----"));
        model.addAttribute("authors", authorService.getAllAuthor());
        return "authors";
    }

    @PostMapping("/authors")
    public String authorsPost(@ModelAttribute("authorName") String authorName,
                              Model model) {
        model.addAttribute("authorName", authorName);
        model.addAttribute("authors", authorService.getAuthorsByName(authorName));
        return "authors";
    }

    @GetMapping("/books")
    public String booksGet(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("bookName", new String());
        return "books";
    }

    @PostMapping("/books")
    public String booksPost(@ModelAttribute("bookName") String bookName,
                              Model model) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("books", bookService.getBookByName(bookName));
        return "books";
    }

    @GetMapping("/books/{id}")
    public String book(Model model, @PathVariable int id) {

        model.addAttribute("authors", authorHasBookService.getAuthorsByBookId(id));
        model.addAttribute("book", bookService.getBookById(id));
        return "book";
    }

    @GetMapping("/authors/{id}")
    public String author(Model model, @PathVariable int id) {

        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("books", authorHasBookService.getBooksByAuthorId(id));
        return "author";
    }

    @GetMapping("/books/create")
    public String createBookGet(Model model) {
        model.addAttribute("book", new Book());
//        model.addAttribute("author", new AuthorEntity());
        System.out.println("create book");
        return "create_book";
    }

    @PostMapping("/books/create")
    public String createBookPost(@ModelAttribute Book book,
                                 Model model) {
        model.addAttribute("book", book);
        // тут робити пост запити
        BookEntity newBook = new BookEntity();
        newBook.setIsbn(book.getIsbn());
        newBook.setName(book.getBookName());
        newBook.setDescription(book.getDescription());

        System.out.println("New Book1 : " + newBook.toString());
        newBook = bookService.addNewBook(newBook);
        System.out.println("New Book2 : " + newBook.toString());

        String[] authors = book.getAuthor().split(",");
        for (String authorName : authors) {
            List authorsList = authorService.findAuthorsByName(authorName);
            int authorId = authorsList.isEmpty()
                    ? authorService.addNewAuthor(authorName).getAuthorId()
                    : ((AuthorEntity) authorsList.get(0)).getAuthorId();
            authorHasBookService.createAuthorHasBook(authorId, newBook.getBookId());
        }

        return "redirect:/books";
    }
}
