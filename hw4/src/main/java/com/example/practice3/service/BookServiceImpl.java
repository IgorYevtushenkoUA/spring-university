package com.example.practice3.service;

import com.example.practice3.dao.BookDao;
import com.example.practice3.dao.BookDaoImpl;
import com.example.practice3.dto.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public List<Book> findBookByName(String name) {
        return bookDao.findBookByName(name);
    }

}
