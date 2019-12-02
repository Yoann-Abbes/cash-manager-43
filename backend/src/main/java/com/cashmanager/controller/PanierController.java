package com.cashmanager.controller;

import com.cashmanager.exception.ResourceNotFoundException;
import com.cashmanager.model.Client;
import com.cashmanager.model.Panier;
import com.cashmanager.repository.ClientRepository;
import com.cashmanager.repository.PanierRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class PanierController {
    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ClientRepository clientRepository;

    //Get all Paniers

    @ApiOperation(value = "View a list of available Paniers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })


    @GetMapping(value = "/paniers")
    public List<Panier> getAllPaniers(){
        return panierRepository.findAll();
    }

    // Get a single panier
    @ApiOperation(value = "Get a panier by id")
    @GetMapping(value = "/paniers/{id}")
    public Optional<Panier> getPanierById(@ApiParam (value = " id to fetch the panier object we want to retrieve", required = true )@PathVariable (value = "id") Long id_panier) {
        return panierRepository.findById(id_panier);
    }

    //Create a new Panier
    @PostMapping(value = "/clients/{client_id}/paniers")
    @ApiOperation(value = "add a Panier")
    public Panier createPanier(@Valid @RequestBody Panier panier,
                               @PathVariable (value = "client_id") Long client_id) {
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client","client_id",client_id));
        panier.setClient(client);
        clientRepository.save(client);
        return panierRepository.save(panier);
    }



    @ApiOperation(value = "Deleting a panier with its id")
    @DeleteMapping (value = "/paniers/{id}")
    public ResponseEntity<?> deletePanier(@PathVariable (value = "id") Long id_panier,
                                          Long client_id) {
        Panier panier = panierRepository.findById(id_panier)
                .orElseThrow(() -> new ResourceNotFoundException("Panier","id_panier", id_panier));
        //for (Produit p : panier.getProduit()){
        //    p.setPanier(null);
        ///    produitRepository.save(p);
        //}
        //Client client= clientRepository.getOne(client_id);
        //client.setPanier(null);
        //clientRepository.save(client);
        panierRepository.delete(panier);
        return ResponseEntity.ok().build();
    }

}