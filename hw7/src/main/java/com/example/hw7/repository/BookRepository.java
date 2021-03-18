package com.example.hw7.repository;

import com.example.hw7.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("SELECT b FROM BookEntity b WHERE b.isbn =:isbn")
    Optional<BookEntity> findBookByISBN(@Param("isbn") String isbn);

    @Query("SELECT b FROM BookEntity b WHERE b.name LIKE:name")
    Optional<BookEntity> findBookByNameLike(@Param("name") String name);

}
