package javaee.hw8.demo.service;

import javaee.hw8.demo.entity.AuthorEntity;
import javaee.hw8.demo.entity.BookEntity;
import javaee.hw8.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<AuthorEntity> findAllAuthor(Pageable pageable) {
        return authorRepository.findAll(pageable);

    }

    public void addAuthor(AuthorEntity author) {
        authorRepository.saveAndFlush(author);
    }

    public void addAuthor(String name) {
        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        authorRepository.saveAndFlush(author);
    }

    public Page<AuthorEntity> findAllAuthorByName(String name, Pageable pageable) {
        return authorRepository.findAllAuthorByName('%' + name + '%', pageable);
    }

    public List<AuthorEntity> findAllAuthorByName(String name) {
        return authorRepository.findAllAuthorByName(name);
    }

    public void addToAuthorBook(AuthorEntity author, BookEntity book) {
        author.getAuthorBooks().add(book);
        authorRepository.save(author);
    }

    public List<BookEntity> findAllAuthorsBook(AuthorEntity author) {
        return author.getAuthorBooks();
    }

    public Optional<AuthorEntity> findAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }
}
