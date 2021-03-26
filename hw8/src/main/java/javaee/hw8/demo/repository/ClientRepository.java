package javaee.hw8.demo.repository;

import javaee.hw8.demo.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Query("select c from ClientEntity c where c.name=:name")
    ClientEntity findClientByUsername(@Param("name") String name);

    @Query("select c from ClientEntity c where c.phoneNumber=:phoneNumber")
    ClientEntity findClientByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select c from ClientEntity  c where c.clientId=:clientId")
    ClientEntity findClientById(@Param("clientId") Integer clientId);

}
