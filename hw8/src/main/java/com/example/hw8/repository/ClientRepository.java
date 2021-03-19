package com.example.hw8.repository;

import com.example.hw8.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

}
