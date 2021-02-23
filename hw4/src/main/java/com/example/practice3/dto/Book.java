package com.example.practice3.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String isbn;
    private String bookName;
    private String author;
}
