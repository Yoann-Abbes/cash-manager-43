package com.cashmanager.controller;

import com.cashmanager.model.Client;
import com.cashmanager.repository.ClientRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Api(value = "Client Controller")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Get All Clients
    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @GetMapping(value = "/clients")
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Create a new Clients
    @PostMapping("/clients")
    @ApiOperation(value = "Add an client")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Get a Single Client by id
    @ApiOperation(value = "Get an Client by Id")
    @GetMapping(value = "/clients/{id}")
    public Client getClientById(@ApiParam(value = "Client id from which client object will retrieve", required = true) @PathVariable (value="id") Long client_id) {
        return clientRepository.findById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", client_id));
    }


    // Update a Client
    @ApiOperation(value = "Update a client")
    @PutMapping(value = "/clients/{idClient}")
    public Client updateClient(@ApiParam(value = "Employee Id to update employee object", required = true)
                               @PathVariable (value = "id") Long client_id,
                               @Valid @RequestBody Client clientDetails) {

        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", client_id));

        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setPanier(clientDetails.getPanier());
        client.setLogin((clientDetails.getLogin()));
        client.setEmail(clientDetails.getEmail());
        client.setMdp(clientDetails.getMdp());
        Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }

    // Delete a Client
    @DeleteMapping(value = "/clients/{idClient}")
    public ResponseEntity<?> deleteClient(@ApiParam(value = "Client Id from which client object will delete from database table", required = true)
                                          @PathVariable(value = "id")  Long client_id) {
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "client_id", client_id));

        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }}


