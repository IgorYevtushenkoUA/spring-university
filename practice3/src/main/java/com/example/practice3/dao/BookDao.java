package com.example.practice3.dao;

import com.example.practice3.dto.Book;

import java.util.List;
import java.util.stream.Stream;

public interface BookDao {
    public List<Book> getAllBooks();

    public void addBook(Book book);

    public List<Book> findBookByName(String name);

}
