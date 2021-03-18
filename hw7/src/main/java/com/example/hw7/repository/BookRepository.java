package com.example.hw7.repository;

import com.example.hw7.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    Page<BookEntity> findAll(Pageable pageable);

    @Query("SELECT b FROM BookEntity b WHERE b.isbn =:isbn")
    Optional<BookEntity> findBookByISBN(@Param("isbn") String isbn);

    @Query("SELECT b FROM BookEntity b WHERE b.name LIKE:name")
    Page<BookEntity> findBooksByNameLike(@Param("name") String name, Pageable pageable);

}
