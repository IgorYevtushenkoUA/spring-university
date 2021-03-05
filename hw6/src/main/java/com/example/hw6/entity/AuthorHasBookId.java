package com.example.hw6.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthorHasBookId implements Serializable {

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "book_id")
    private Integer bookId;

}
