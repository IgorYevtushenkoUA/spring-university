package com.example.hw6.dao;

import com.example.hw6.entity.AuthorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl {
    private final EntityManager entityManager;

    /** add new Author*/
    public AuthorEntity addNewAuthor(String name){
        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        return entityManager.merge(author);
    }

    /**
     * get all author
     */
    public List<AuthorEntity> getAllAuthor() {
        System.out.println("getAllAuthor");
        return entityManager.createQuery("SELECT a FROM AuthorEntity a", AuthorEntity.class)
                .getResultList();
    }

    /**
     * get author by ID
     */
    public AuthorEntity getAuthorById(int authorId) {
        System.out.println("getAuthorById");
        return entityManager.find(AuthorEntity.class, authorId);
    }

    /**
     * delete author by ID
     */
    public void deleteAuthorById(int authorId) {
        System.out.println("deleteAuthorById");
        entityManager.createQuery("DELETE FROM AuthorEntity a WHERE a.authorId=:authorId", AuthorEntity.class)
                .setParameter("authorId", authorId)
                .executeUpdate();
    }

    /**
     * edit author
     */
    public void editAuthor(AuthorEntity author) {
        System.out.println("editAuthor");
        entityManager.merge(author);
    }

    /** get authors with similar name*/
    public List findAuthorsByName(String authorName) {
        System.out.println("findAuthorsByName");
        return entityManager.createQuery("SELECT a FROM AuthorEntity a " +
                "WHERE a.name LIKE :authorName", AuthorEntity.class)
                .setParameter("authorName", '%' + authorName + '%')
                .getResultList();
    }
}
