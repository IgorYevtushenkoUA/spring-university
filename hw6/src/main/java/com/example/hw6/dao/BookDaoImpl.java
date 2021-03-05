package com.example.hw6.dao;

import com.example.hw6.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl {

    private final EntityManager entityManager;

    /** add new book */
    public void addNewBook(String isbn, String name, String description){
        System.out.println("addNewBook");
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setName(name);
        book.setDescription(description);
        entityManager.merge(book);
    }

    /** get all books */
    public List<BookEntity> getAllBooks(){
        System.out.println("addNewBook");
        return entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class)
                .getResultList();
    }

    /** get book by id */
    // todo may can be mistakes check
    public BookEntity getBookById(int bookId){
        System.out.println("getBookById");
        return entityManager.createQuery("SELECT b FROM BookEntity b WHERE b.bookId=:bookId", BookEntity.class)
                .setParameter("bookId", bookId)
                .getSingleResult();
    }

    /** edit book */
    public void editBook(BookEntity book){
        entityManager.merge(book);
    }

    /** get books by name */
    public List<BookEntity> getBookByName(String name){
        System.out.println("getBookByName");
        return entityManager.createQuery("SELECT b FROM BookEntity b WHERE b.name LIKE name", BookEntity.class)
                .setParameter("name", '%'+name+'%')
                .getResultList();
    }
    /** get book by  isbn */
    public BookEntity getBookByIsbn(String isbn){
        System.out.println("getBookByIsbn");
        return entityManager.createQuery("SELECT b FROM BookEntity b WHERE b.isbn=:isbn", BookEntity.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    /** delete book by ID*/
    public void deleteBookById(int bookId){
        System.out.println("deleteBookById");
        entityManager.createQuery("DELETE FROM BookEntity b WHERE b.bookId=:bookId ", BookEntity.class)
                .setParameter("bookId", bookId)
                .executeUpdate();
    }

}
