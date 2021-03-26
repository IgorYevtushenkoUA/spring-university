package javaee.hw8.demo.service;

import javaee.hw8.demo.entity.AuthorEntity;
import javaee.hw8.demo.entity.BookEntity;
import javaee.hw8.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
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

    public Page<BookEntity> findBooksByISBN(String isbn, Pageable pageable) {
        return bookRepository.findBooksByISBN('%' + isbn + '%', pageable);
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


    public Page<BookEntity> findBooksByNameLike(String name, Pageable pageable) {
        return bookRepository.findBooksByNameLike('%' + name + '%', pageable);
    }

    public Page<BookEntity> findBooksByISBNAndName(String isbnName, String bookName, Pageable pageable) {
        return bookRepository.findBooksByISBNAndName('%' + isbnName + '%', '%' + bookName + '%', pageable);
    }


    public List<AuthorEntity> findBookAuthors(BookEntity book) {
        return book.getAuthors();
    }

}
