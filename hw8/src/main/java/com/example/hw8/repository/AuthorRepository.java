package com.example.hw8.repository;

import com.example.hw8.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    Page<AuthorEntity> findAll(Pageable pageable);

    @Query("SELECT a FROM AuthorEntity a WHERE a.name LIKE:name")
    Page<AuthorEntity> findAllAuthorByName(@Param("name") String name,Pageable pageable);

    @Query("SELECT a FROM AuthorEntity a WHERE a.name=:name")
    List<AuthorEntity> findAllAuthorByName(@Param("name") String name);

}


