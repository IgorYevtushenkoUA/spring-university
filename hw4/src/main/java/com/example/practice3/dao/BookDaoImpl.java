package com.example.practice3.dao;

import com.example.practice3.dto.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDaoImpl implements BookDao{

    private static List<Book> bookStore = new ArrayList<>();

    static{
        bookStore.add(new Book("1243","book1","author1"));
        bookStore.add(new Book("1248","book2","author2"));
        bookStore.add(new Book("1211","book3","author3"));
        bookStore.add(new Book("9243","book4","author4"));
        bookStore.add(new Book("7543","book5","author5"));
    }

    @Override
    public List<Book> getAllBooks(){
        return bookStore;
    }

    @Override
    public void addBook(Book book){
        bookStore.add(book);
    }

    @Override
    public List<Book> findBookByName(String name) {
        List<Book> books = bookStore.stream()
                .filter(book -> book.getBookName().contains(name))
                .collect(Collectors.toList());
        return books;
    }
}
