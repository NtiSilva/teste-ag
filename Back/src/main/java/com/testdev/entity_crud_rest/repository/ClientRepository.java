package com.testdev.entity_crud_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testdev.entity_crud_rest.modelo.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}