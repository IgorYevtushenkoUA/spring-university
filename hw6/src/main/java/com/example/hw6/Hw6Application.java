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
        SpringApplication.run(Hw6Application.class, args);
    }
}