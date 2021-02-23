package kma.yevtushenko.pr3.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String ISBN;
    private String book;
    private String author;

    public Book(String i, String book, String author) {
        this.ISBN = i;
        this.book = book;
        this.author = author;
    }

    public Book(){}
}
