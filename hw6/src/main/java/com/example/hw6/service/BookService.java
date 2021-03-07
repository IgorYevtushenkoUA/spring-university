package com.example.hw6.service;

import com.example.hw6.dao.BookDaoImpl;
import com.example.hw6.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDaoImpl bookDao;

    @Transactional
    /** add new book */
    public BookEntity addNewBook(String isbn, String name, String description) {
        return bookDao.addNewBook(isbn, name, description);
    }

    @Transactional
    public BookEntity addNewBook(BookEntity bookEntity) {
        return bookDao.addNewBook(bookEntity);
    }

    /**
     * get all books
     */
    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookDao.getAllBooks();
    }

    /**
     * get book by id
     */
    // todo may can be mistakes check
    @Transactional
    public BookEntity getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }

    /**
     * edit book
     */
    @Transactional
    public void editBook(BookEntity book) {
        bookDao.editBook(book);
    }

    /**
     * get books by name
     */
    @Transactional
    public List<BookEntity> getBookByName(String name) {
        return bookDao.getBookByName(name);
    }

    /**
     * get book by  isbn
     */
    @Transactional
    public BookEntity getBookByIsbn(String isbn) {

        return bookDao.getBookByIsbn(isbn);
    }

    /**
     * delete book by ID
     */
    @Transactional
    public void deleteBookById(int bookId) {
        bookDao.deleteBookById(bookId);
    }


}
