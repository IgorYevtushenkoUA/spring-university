package com.example.hw6;

import com.example.hw6.service.AuthorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hw6Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Hw6Application.class, args);
		authorTest(applicationContext);
	}

	public static void authorTest(ApplicationContext applicationContext){
		AuthorService authorService = applicationContext.getBean(AuthorService.class);
		System.out.println(authorService.getAllAuthor());
		System.out.println(authorService.getAuthorById(1));
		System.out.println(authorService.findAuthorsByName("раф"));

	}

}