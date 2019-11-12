package com.cashmanager.controller;

import com.cashmanager.model.Panier;
import com.cashmanager.repository.PanierRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PanierController {
    @Autowired
    private PanierRepository panierRepository;

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

    //Create a new Panier
    @PostMapping(value = "/paniers")
    @ApiOperation(value = "add a Panier")
    public Panier createPanier(@Valid @RequestBody Panier panier) {
        return panierRepository.save(panier);
    }


    // Get a single panier
    @ApiOperation(value = "Get a panier by id")
    @GetMapping(value = "/paniers/{idPanier}")
    public Panier getPanierById(@ApiParam (value = " Client Id linked to the panier object we want to retrieve", required = true )@PathVariable Long idClient) {
        return (Panier) panierRepository.getbyidclient(idClient);
    }

    // Update a Panier
    //@ApiOperation(value = "Update a panier")
    //@PutMapping(value = "/paniers/{id_panier}")
    //public Panier updatePanier(@ApiParam(value = "Id of the panier to update", required = true)@PathVariable Long id_panier,
    //                              @Valid @RequestBody Panier panierDetails) {
    //    Panier panier = panierRepository.findById(id_panier)
    //            .orElseThrow(() -> new ResourceNotFoundException("id_panier", "id_panier", id_panier));
    //    panier.setIdClient(panierDetails.getIdClient());
    //    panier.setIdPanier(panierDetails.getIdPanier());
    //   panier.setIdProduit(panierDetails.getIdProduit());
    //   Panier updatedPanier = panierRepository.save(panier);
    //return updatedPanier;
    //}

    @DeleteMapping (value = "/paniers/{idClient}")
    public ResponseEntity<?> deletePanier(@ApiParam(value = "Client Id from which panier object will deleted from the database", required = true)@PathVariable Long idClient) {
        List<Panier> paniers = panierRepository.getbyidclient(idClient);
        for (Panier p : paniers) {
            panierRepository.deletebyidclient(p.getIdClient());
        }
        return ResponseEntity.ok().build();
    }

}