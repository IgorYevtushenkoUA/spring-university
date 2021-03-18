package com.example.hw7;

import com.example.hw7.controller.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hw7Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Hw7Application.class, args);
		testBook(applicationContext);
	}

	public static void testBook(ApplicationContext applicationContext){
		BookService bookService = applicationContext.getBean(BookService.class);
		System.out.println(bookService.getAllBooks());
	}

}
