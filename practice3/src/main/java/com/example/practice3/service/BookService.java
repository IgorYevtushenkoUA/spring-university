package com.example.practice3.service;

import com.example.practice3.dto.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();

    public void addBook(Book book);

    public List<Book> findBookByName(String name);

}
