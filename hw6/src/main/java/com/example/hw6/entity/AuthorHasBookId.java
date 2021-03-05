package com.example.hw6.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthorHasBookId {

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "book_id")
    private Integer bookId;

}
