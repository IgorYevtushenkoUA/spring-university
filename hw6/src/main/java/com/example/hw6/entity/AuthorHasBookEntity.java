package com.example.hw6.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "author_has_etity")
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorHasBookEntity {

    @EmbeddedId
    private AuthorHasBookId authorHasBookId;
}
