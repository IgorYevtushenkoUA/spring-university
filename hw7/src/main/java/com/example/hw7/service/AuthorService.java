package com.example.hw7.service;

import com.example.hw7.entity.AuthorEntity;
import com.example.hw7.entity.BookEntity;
import com.example.hw7.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void addAuthor(AuthorEntity author) {
        authorRepository.saveAndFlush(author);
    }

    public void addAuthor(String name) {
        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        authorRepository.saveAndFlush(author);
    }

    public void deleteAuthorById(int authorId) {
        authorRepository.deleteById(authorId);
    }

    public void findAllAuthorByName(String name) {
        authorRepository.findAllAuthorByName(name);
    }

    public void addToAuthorBook(AuthorEntity author, BookEntity book) {
        author.getAuthorBooks().add(book);
        authorRepository.save(author);
    }

    public Optional<AuthorEntity> findAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }
}
