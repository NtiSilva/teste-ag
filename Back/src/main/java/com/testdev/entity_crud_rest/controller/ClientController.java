package com.testdev.entity_crud_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.testdev.entity_crud_rest.modelo.Client;
import com.testdev.entity_crud_rest.repository.ClientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
@Validated
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Adicionar um novo cliente
    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Editar um cliente existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<Client> editClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id)
            .orElseThrow();

        client.setName(clientDetails.getName());
        client.setEmail(clientDetails.getEmail());
        client.setPhone(clientDetails.getPhone());
        client.setAddress(clientDetails.getAddress());
        client.setCompany(clientDetails.getCompany());

        Client updatedClient = clientRepository.save(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    // Visualizar um cliente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // Excluir um cliente pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientRepository.findById(id)
            .orElseThrow();
        clientRepository.deleteById(id);
        return new ResponseEntity<>("Cliente excluido com sucesso!!", HttpStatus.OK);
    }
}
