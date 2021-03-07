package com.example.hw6.service;

import com.example.hw6.dao.AuthorDaoImpl;
import com.example.hw6.entity.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorDaoImpl authorDao;

    @Transactional
    public AuthorEntity addNewAuthor(String name){
        return authorDao.addNewAuthor(name);
    }

    @Transactional
    public List<AuthorEntity> getAllAuthor() {
        return authorDao.getAllAuthor();
    }

    /**
     * get author by ID
     */
    @Transactional
    public AuthorEntity getAuthorById(int authorId) {
        return authorDao.getAuthorById(authorId);
    }

    /**
     * delete author by ID
     */
    @Transactional
    public void deleteAuthorById(int authorId) {
        authorDao.deleteAuthorById(authorId);
    }

    /**
     * edit author
     */
    @Transactional
    public void editAuthor(AuthorEntity author) {
        authorDao.editAuthor(author);
    }

    /**
     * get authors with similar name
     */
    @Transactional
    public List findAuthorsByName(String authorName) {
        return authorDao.findAuthorsByName(authorName);
    }
}
