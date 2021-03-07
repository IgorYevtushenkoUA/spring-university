package com.example.hw6;

import com.example.hw6.entity.BookEntity;
import com.example.hw6.service.AuthorHasBookService;
import com.example.hw6.service.AuthorService;
import com.example.hw6.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hw6Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Hw6Application.class, args);
//		authorTest(applicationContext);
//		authorHasBookTest(applicationContext);
//        bookTest(applicationContext);
    }

    public static void authorTest(ApplicationContext applicationContext) {
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
//		System.out.println(authorService.addNewAuthor("testAuthor"));
        System.out.println(authorService.getAllAuthor());
        System.out.println(authorService.getAuthorById(1));
        System.out.println(authorService.findAuthorsByName("рафsdfsdfsdfsdfsdfsdf"));
    }

    public static void authorHasBookTest(ApplicationContext applicationContext) {
        AuthorHasBookService authorHasBookService = applicationContext.getBean(AuthorHasBookService.class);
        System.out.println(authorHasBookService.getBooksByAuthorId(1));
    }

    public static void bookTest(ApplicationContext applicationContext) {
        BookService bookService = applicationContext.getBean(BookService.class);
        BookEntity book = new BookEntity();
        book.setIsbn("123");
        book.setName("123");
        book.setDescription("123");

//        bookService.addNewBook(book);
        System.out.println("book was added");

    }
}