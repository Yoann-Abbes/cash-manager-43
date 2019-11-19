package com.cashmanager.controller;

import com.cashmanager.exception.ResourceNotFoundException;
import com.cashmanager.model.Panier;
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
    @GetMapping(value = "/paniers/{id_panier}")
    public Optional<Panier> getPanierById(@ApiParam (value = " id to fetch the panier object we want to retrieve", required = true )@PathVariable Long id_panier) {
        return panierRepository.findById(id_panier);
    }

    // Update a Panier
    @ApiOperation(value = "Update a panier")
    @PutMapping(value = "/paniers/{id_panier}")
    public Panier updatePanier(@ApiParam(value = "Id of the panier to update", required = true)@PathVariable Long id_panier,
                               @Valid @RequestBody Panier panierDetails) {
        Panier panier = panierRepository.findById(id_panier)
                .orElseThrow(() -> new ResourceNotFoundException("idPanier", "idPanier", id_panier));
        panier.setClient(panierDetails.getClient());
        panier.setIdPanier(panierDetails.getIdPanier());
        panier.setProduit(panierDetails.getProduit());
        Panier updatedPanier = panierRepository.save(panier);
        return updatedPanier;
    }


    @DeleteMapping (value = "/produits/{idProduit}")
    public ResponseEntity<?> deletePanier(@PathVariable Long id_panier) {
        Panier panier = panierRepository.findById(id_panier)
                .orElseThrow(() -> new ResourceNotFoundException("Panier","id_panier", id_panier));
        panierRepository.delete(panier);
        return ResponseEntity.ok().build();
    }

}