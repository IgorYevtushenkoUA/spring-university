package com.example.hw6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Book {

    private String isbn;
    private String bookName;
    private String description;
    private String author;

}
