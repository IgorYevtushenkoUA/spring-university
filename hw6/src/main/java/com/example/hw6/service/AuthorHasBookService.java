package com.example.hw6.service;

import com.example.hw6.dao.AuthorHasBookDaoImpl;
import com.example.hw6.entity.AuthorHasBookEntity;
import com.example.hw6.entity.AuthorHasBookId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorHasBookService {

    @Autowired
    private AuthorHasBookDaoImpl authorHasBookDao;

    /**
     * edit
     */
    @Transactional
    public void editAuthorHasBook(int authorId, int oldBookId, int newBookId) {
        authorHasBookDao.editAuthorHasBook(authorId, oldBookId, newBookId);
    }

    /**
     * create
     */
    @Transactional
    public void createAuthorHasBook(int authorId, int bookId) {
        authorHasBookDao.createAuthorHasBook(authorId, bookId);
    }

    /**
     * delete
     */
    @Transactional
    public void deleteAuthorHasBook(int authorId, int bookId) {
        authorHasBookDao.createAuthorHasBook(authorId, bookId);
    }

    /**
     * get books author by bookID
     */
    @Transactional
    public List getAuthorsByBookId(int bookId) {
        return authorHasBookDao.getAuthorsByBookId(bookId);
    }

}
