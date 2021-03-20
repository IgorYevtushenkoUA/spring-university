package com.example.hw8.service;

import com.example.hw8.entity.BookEntity;
import com.example.hw8.entity.ClientEntity;
import com.example.hw8.entity.RoleEntity;
import com.example.hw8.repository.ClientRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    @Getter
    @Setter
    private ClientEntity client;

    private final ClientRepository clientRepository;

    public ClientEntity findClientByUsername(String username) {
        return clientRepository.findClientByUsername(username);
    }

    public ClientEntity findClientById(int clientId) {
        return clientRepository.findClientById(clientId);
    }

    public ClientEntity findClientByPhoneNumber(String phoneNumber) {
        return clientRepository.findClientByPhoneNumber(phoneNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("SURNAME ::::" + username + "::::");

        setClient(clientRepository.findClientByUsername(username));
//                .orElseThrow(() -> new UsernameNotFoundException("No client with username: " + username));

        System.out.println("Client ::: " + client);

        if (client == null) throw new UsernameNotFoundException("Unknown user: " + username);


        UserDetails user = User.builder()
                .username(client.getName())
                .password(client.getPassword())
                .roles(client.getClientRoles().getRoleName())
                .build();
        return user;
    }

    public void addFavouriteBook(ClientEntity client, BookEntity book) {
        if (client.getClientBooks()
                .stream()
                .filter(bookEntity -> bookEntity.getBookId() == book.getBookId())
                .count() == 0) {
            client.getClientBooks().add(book);
        }

        clientRepository.save(client);
    }

    public void removeFavouriteBook(ClientEntity client, BookEntity book) {
        client.setClientBooks(client.getClientBooks()
                .stream()
                .filter(bookItem -> bookItem.getBookId() != book.getBookId())
                .collect(Collectors.toList()));
        clientRepository.save(client);
    }

    public void removeFavouriteBookById(ClientEntity client, int bookId) {
        client.setClientBooks(client.getClientBooks()
                .stream()
                .filter(bookItem -> bookItem.getBookId() != bookId)
                .collect(Collectors.toList()));
        clientRepository.save(client);
    }
}
