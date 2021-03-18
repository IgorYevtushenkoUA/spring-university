package com.example.hw7.controller;

import com.example.hw7.entity.BookEntity;
import com.example.hw7.service.AuthorService;
import com.example.hw7.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

//    @Autowired
//    private AuthorHasBookService authorHasBookService;

    @GetMapping("/")
    public String main(Model model, Pageable pageable) {

        model.addAttribute("books", bookService.findAllBooks(pageable));
        model.addAttribute("authors", authorService.findAllAuthor());
        return "main";
    }

    @GetMapping("/authors")
    public String authorsGet(Model model) {

        model.addAttribute("authorName", new String("have name----"));
        model.addAttribute("authors", authorService.findAllAuthor());
        return "authors";
    }

    @PostMapping("/authors")
    public String authorsPost(@ModelAttribute("authorName") String authorName,
                              Model model) {
        model.addAttribute("authorName", authorName);
        model.addAttribute("authors", authorService.findAllAuthorByName(authorName));
        return "authors";
    }

    @GetMapping("/books")
    public String booksGet(@PageableDefault(size = 6) Pageable pageable, Model model) {
        Page<BookEntity> page = bookService.findAllBooks(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pageNum", page.getNumber());
        model.addAttribute("bookName", new String());

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "books";
    }

    @PostMapping("/books")
    public String booksPost(@ModelAttribute("bookName") String bookName,
                            Model model) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("books", bookService.findBookByNameLike(bookName));
        return "books";
    }

//    @GetMapping("/books/{id}")
//    public String book(Model model, @PathVariable int id) {
//
//        model.addAttribute("authors", authorHasBookService.getAuthorsByBookId(id));
//        model.addAttribute("book", bookService.getBookById(id));
//        return "book";
//    }
//
//    @GetMapping("/authors/{id}")
//    public String author(Model model, @PathVariable int id) {
//
//        model.addAttribute("author", authorService.getAuthorById(id));
//        model.addAttribute("books", authorHasBookService.getBooksByAuthorId(id));
//        return "author";
//    }
//
//    @GetMapping("/books/create")
//    public String createBookGet(Model model) {
//        model.addAttribute("book", new Book());
////        model.addAttribute("author", new AuthorEntity());
//        System.out.println("create book");
//        return "create_book";
//    }
//
//    @PostMapping("/books/create")
//    public String createBookPost(@ModelAttribute Book book,
//                                 Model model) {
//        model.addAttribute("book", book);
//        // тут робити пост запити
//        BookEntity newBook = new BookEntity();
//        newBook.setIsbn(book.getIsbn());
//        newBook.setName(book.getBookName());
//        newBook.setDescription(book.getDescription());
//
//        System.out.println("New Book1 : " + newBook.toString());
//        newBook = bookService.addNewBook(newBook);
//        System.out.println("New Book2 : " + newBook.toString());
//
//        String[] authors = book.getAuthor().split(",");
//        for (String authorName : authors) {
//            List authorsList = authorService.findAuthorsByName(authorName);
//            int authorId = authorsList.isEmpty()
//                    ? authorService.addNewAuthor(authorName).getAuthorId()
//                    : ((AuthorEntity) authorsList.get(0)).getAuthorId();
//            authorHasBookService.createAuthorHasBook(authorId, newBook.getBookId());
//        }
//
//        return "redirect:/books";
//    }
}

