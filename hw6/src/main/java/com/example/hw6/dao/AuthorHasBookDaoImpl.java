package com.example.hw6.dao;

import com.example.hw6.entity.AuthorHasBookEntity;
import com.example.hw6.entity.AuthorHasBookId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AuthorHasBookDaoImpl {

    private final EntityManager entityManager;

    /**
     * edit
     */
    public void editAuthorHasBook(int authorId, int oldBookId, int newBookId) {
        System.out.println("editAuthorHasBook");
        deleteAuthorHasBook(authorId, oldBookId);
        createAuthorHasBook(authorId, newBookId);
    }

    /**
     * create
     */
    public void createAuthorHasBook(int authorId, int bookId) {
        System.out.println("createAuthorHasBook");
        AuthorHasBookEntity authorHasBookEntity = new AuthorHasBookEntity(new AuthorHasBookId(authorId, bookId));
        entityManager.merge(authorHasBookEntity);
    }

    /**
     * delete
     */
    public void deleteAuthorHasBook(int authorId, int bookId) {
        System.out.println("deleteAuthorHasBook");
        entityManager.createQuery("DELETE FROM AuthorHasBookEntity ahb " +
                "WHERE ahb.authorHasBookId.authorId=:authorId " +
                "AND ahb.authorHasBookId.bookId=:bookId", AuthorHasBookDaoImpl.class)
                .setParameter("authorId", authorId)
                .setParameter("bookId", bookId)
                .executeUpdate();
    }
}
