package kma.yevtushenko.pr3.service;

import kma.yevtushenko.pr3.dao.BookDao;
import kma.yevtushenko.pr3.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public void addNewBook(Book book) {
        bookDao.addNewBook(book);
    }
}
