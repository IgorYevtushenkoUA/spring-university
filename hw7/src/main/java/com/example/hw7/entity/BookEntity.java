package com.example.hw7.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class BookEntity {

    @Id
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "book")
    List<UserEntity> users;

    @ManyToMany(mappedBy = "book")
    List<AuthorEntity> authors;

}
