package com.example.hw7.service;

import com.example.hw7.entity.AuthorEntity;
import com.example.hw7.entity.BookEntity;
import com.example.hw7.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<BookEntity> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Optional<BookEntity> findAllBookById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    public void addBook(BookEntity book) {
        bookRepository.save(book);
    } // saveAndFlush

    public void addBook(String isbn, String name, String description) {
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setName(name);
        book.setDescription(description);
        bookRepository.save(book);//saveAndFlush
    }

    public Optional<BookEntity> findBookByISBN(String isbn) {
        return bookRepository.findBookByISBN(isbn);
    }

    public Page<BookEntity> findBooksByNameLike(String name,Pageable pageable) {
        return bookRepository.findBooksByNameLike(name,pageable);
    }

    public List<AuthorEntity> findBookAuthors(BookEntity book){
        return book.getAuthors();
    }

}
