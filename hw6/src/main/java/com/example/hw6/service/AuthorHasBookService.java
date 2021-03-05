package com.example.hw6.service;

import com.example.hw6.dao.AuthorHasBookDaoImpl;
import com.example.hw6.entity.AuthorHasBookEntity;
import com.example.hw6.entity.AuthorHasBookId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorHasBookService {

    @Autowired
    private AuthorHasBookDaoImpl authorHasBookDao;

    /**
     * edit
     */
    public void editAuthorHasBook(int authorId, int oldBookId, int newBookId) {
        authorHasBookDao.editAuthorHasBook(authorId, oldBookId, newBookId);
    }

    /**
     * create
     */
    public void createAuthorHasBook(int authorId, int bookId) {
        authorHasBookDao.createAuthorHasBook(authorId, bookId);
    }

    /**
     * delete
     */
    public void deleteAuthorHasBook(int authorId, int bookId) {
        authorHasBookDao.createAuthorHasBook(authorId, bookId);
    }

}
