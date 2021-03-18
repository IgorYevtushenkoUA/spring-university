package com.example.hw7.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString(exclude = "role")
public class ClientEntity {

    @Id
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name="role_id")
    private String roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;

    @ManyToMany
    @JoinTable(name = "client_has_favorite_book",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> clientBooks;

}
