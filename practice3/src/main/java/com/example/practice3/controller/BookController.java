package com.example.practice3.controller;

import com.example.practice3.dto.Book;
import com.example.practice3.dto.ServiceResponse;
import com.example.practice3.service.BookService;
import com.example.practice3.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The @RestController annotation was introduced in Spring 4.0 to simplify the creation of RESTful web services. It's a convenience annotation that combines @Controller and @ResponseBody – which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
 * Classic controllers can be annotated with the @Controller annotation. This is simply a specialization of the @Component class and allows implementation classes to be autodetected through the classpath scanning.
 *
 * @Controller is typically used in combination with a @RequestMapping annotation used on request handling methods.
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService = new BookServiceImpl();

    @PostMapping("/add-book")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        System.out.println(bookService.getAllBooks());
        ServiceResponse<Book> response = new ServiceResponse<>("success", book);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/get-books")
    public ResponseEntity<Object> getAllBooks() {
        ServiceResponse<List<Book>> response = new ServiceResponse<>("success", bookService.getAllBooks());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/search-books")
    public ResponseEntity<Object> searchBook(@RequestParam(name = "searchName", required = false) String searchName) {
        List<Book> books = bookService.findBookByName(searchName);
        ServiceResponse<List<Book>> response = new ServiceResponse<>("success", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
