package com.cashmanager.controller;

import com.cashmanager.model.Client;
import com.cashmanager.repository.ClientRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Get All Clients
    @GetMapping(value = "/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Create a new Clients
    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Get a Single Client by id
    @GetMapping(value = "/clients/{idClient}")
    public Client getClientById(@PathVariable Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "idClient", idClient));
    }


    // Update a Client
    @PutMapping(value = "/clients/{idClient}")
    public Client updateClient(@PathVariable Long idClient,
                             @Valid @RequestBody Client clientDetails) {

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "idClient", idClient));

        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());

        Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }

    // Delete a Client
    @DeleteMapping(value = "/clients/{idClient}")
    public ResponseEntity<?> deleteClient(@PathVariable Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "idClient", idClient));

        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }}


