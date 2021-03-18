package com.example.hw7.service;

import com.example.hw7.entity.BookEntity;
import com.example.hw7.entity.ClientEntity;
import com.example.hw7.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void addToClientFavouriteBook(ClientEntity client, BookEntity book) {
        client.getClientBooks().add(book);
        clientRepository.save(client);
    }

}
