package com.example.hw7.service;

import com.example.hw7.entity.BookEntity;
import com.example.hw7.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService{

    private final BookRepository bookRepository;

    public List<BookEntity> findAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<BookEntity> findAllBookById(Integer bookId){
        return bookRepository.findById(bookId);
    }

    public void addBook(BookEntity book){
        bookRepository.saveAndFlush(book);
    }
    public void addBook(String isbn, String name, String description){
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setName(name);
        book.setDescription(description);
        bookRepository.saveAndFlush(book);
    }

    public Optional<BookEntity> findBookByISBN(String isbn){
        return bookRepository.findBookByISBN(isbn);
    }

    public Optional<BookEntity> findBookByNameLike(String name){
        return bookRepository.findBookByNameLike(name);
    }

    public void deleteBookById(int bookId){
        try {
            bookRepository.deleteById(bookId);
            System.out.println("delete book YES");
        }catch(EmptyResultDataAccessException e){
            System.out.println("book with this id doesn't exists");
        }
    }

}
