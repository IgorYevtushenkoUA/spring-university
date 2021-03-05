package com.example.hw6.dao;

import com.example.hw6.entity.AuthorEntity;
import com.example.hw6.entity.AuthorHasBookEntity;
import com.example.hw6.entity.AuthorHasBookId;
import com.example.hw6.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    /**
     * get books author by bookID
     *
     * @return
     */
    public List getAuthorsByBookId(int bookId) {

        return entityManager.createQuery(
                "SELECT a FROM AuthorEntity a " +
                   "WHERE a.authorId in " +
                        "(SELECT ahb.authorHasBookId.authorId " +
                        "FROM AuthorHasBookEntity ahb " +
                        "WHERE ahb.authorHasBookId.bookId = :bookId )",
                AuthorEntity.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    /** get books by authorId*/
    public List getBooksByAuthorId(int authorId) {

        return entityManager.createQuery(
                "SELECT b FROM BookEntity b " +
                        "WHERE b.bookId in " +
                        "(SELECT ahb.authorHasBookId.bookId " +
                        "FROM AuthorHasBookEntity ahb " +
                        "WHERE ahb.authorHasBookId.authorId = :authorId )",
                BookEntity.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }

}
