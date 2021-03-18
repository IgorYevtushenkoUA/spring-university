package com.example.hw7.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@ToString
public class AuthorEntity {
    @Id
    @Column(name = "author_id")
    private Integer bookId;

    @Column(name = "name")
    private String isbn;

    @ManyToMany
    @JoinTable(name = "author_has_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> authorBooks;

}
