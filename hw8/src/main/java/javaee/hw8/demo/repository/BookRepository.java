package javaee.hw8.demo.repository;

import javaee.hw8.demo.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    Page<BookEntity> findAll(Pageable pageable);

    @Query("SELECT b FROM BookEntity b WHERE b.isbn LIKE :isbn")
    Page<BookEntity> findBooksByISBN(@Param("isbn") String isbn, Pageable pageable);

    @Query("SELECT b FROM BookEntity b WHERE b.name LIKE:name")
    Page<BookEntity> findBooksByNameLike(@Param("name") String name, Pageable pageable);

    @Query("SELECT b FROM BookEntity b WHERE b.name LIKE:name or b.isbn LIKE :isbn")
    Page<BookEntity> findBooksByISBNAndName(@Param("name") String name,
                                            @Param("isbn") String isbn,
                                            Pageable pageable);
}
