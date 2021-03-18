package com.example.hw7.controller;

import com.example.hw7.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;

    public List getAllBooks() {
        return entityManager.createQuery("select b from BookEntity b", BookEntity.class)
                .getResultList();
    }


}
