package com.example.hw8;

import com.example.hw8.entity.BookEntity;
import com.example.hw8.entity.ClientEntity;
import com.example.hw8.service.AuthorService;
import com.example.hw8.service.BookService;
import com.example.hw8.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hw8Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Hw8Application.class, args);
        testClient(applicationContext);


    }

    public static  void testClient(ApplicationContext applicationContext){
        ClientService clientService = applicationContext.getBean(ClientService.class);
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(clientService.findClientByUsername("client").getClientRoles().getRoleName());

        ClientEntity client = clientService.findClientById(1);
        BookEntity book = bookService.findAllBookById(6).stream().findFirst().orElse(null);
//        System.out.println("add book");
//        clientService.addFavouriteBook(client, book);
//        System.out.println("was added book");
        System.out.println("delete book");
        clientService.removeFavouriteBook(client, book);
        System.out.println("was deleted book");


    }

}
