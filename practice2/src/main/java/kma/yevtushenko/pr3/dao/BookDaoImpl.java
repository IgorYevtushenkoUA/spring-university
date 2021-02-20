package kma.yevtushenko.pr3.dao;

import kma.yevtushenko.pr3.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static List<Book> books;

    static {
        books = new ArrayList<>();
        books.add(new Book("1", "book1", "author1"));
        books.add(new Book("2", "book2", "author2"));
        books.add(new Book("3", "book3", "author3"));
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addNewBook(Book book) {
        books.add(book);
    }
}
