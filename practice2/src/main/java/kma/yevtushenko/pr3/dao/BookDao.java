package kma.yevtushenko.pr3.dao;

import kma.yevtushenko.pr3.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();

    void addNewBook(Book book);

}
