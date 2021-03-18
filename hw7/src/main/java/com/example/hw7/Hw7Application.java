package com.example.hw7;

import com.example.hw7.entity.AuthorEntity;
import com.example.hw7.entity.BookEntity;
import com.example.hw7.service.AuthorService;
import com.example.hw7.service.BookService;
import com.example.hw7.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hw7Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Hw7Application.class, args);
        testBook(applicationContext);
    }

    public static void testBook(ApplicationContext applicationContext) {
        AuthorService authorService = applicationContext.getBean(AuthorService.class);
        ClientService clientService = applicationContext.getBean(ClientService.class);
//        System.out.println("get all");
//        System.out.println(bookService.findAllBooks());
//-------------------------------------------------------
//        System.out.println("get by id");
//        System.out.println(bookService.findAllBookById(2));
//-------------------------------------------------------
//		System.out.println("add new book");
//		BookEntity book = new BookEntity();
//		book.setIsbn("isbn");
//		book.setName("name");
//		book.setDescription("description");
//		bookService.addBook(book);
//-------------------------------------------------------
//        System.out.println("search by isbn");
//        System.out.println(bookService.findBookByISBN("isbn"));
//-------------------------------------------------------
//        System.out.println("search  by name");
//        System.out.println(bookService.findBookByNameLike("name"));
//-------------------------------------------------------
//        System.out.println("delete all");
//        bookService.deleteBookById(6);

//-------------------------------------------------------
//        System.out.println("get all");
//        AuthorEntity author = authorService.findAuthorById(1).stream().findFirst().orElse(null);;
//        BookService bookService = applicationContext.getBean(BookService.class);
//        BookEntity book = bookService.findAllBookById(3).stream().findFirst().orElse(null);
//
//        author.getAuthorBooks().add(book);
//        author = authorService.update(author);
//        authorService.addToAuthorBook(author, book);

//-------------------------------------------------------

    }

}
