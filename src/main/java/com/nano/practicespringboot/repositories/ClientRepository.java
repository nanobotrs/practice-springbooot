package com.nano.practicespringboot.repositories;

import com.nano.practicespringboot.entities.Address;
import com.nano.practicespringboot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE c.idNumber = COALESCE(:idNumber, c.idNumber) AND c.names = COALESCE(:names, c.names)")
    List<Client> getByParamerters(String idNumber, String names);

    Boolean existsByIdNumber (String idNumber);

    @Query("SELECT a " +
            "FROM Address a " +
            "WHERE a.client.id = :id " +
            "AND a.type = '1'")
    List<Address> getAddressesByClient(Long id);
}
