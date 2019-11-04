package com.cashmanager.controller;

import com.cashmanager.model.Paiement;
import com.cashmanager.repository.PaiementRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PaiementController {
    @Autowired
    private PaiementRepository paiementRepository;

    //Get all Paiements

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })


    @GetMapping(value = "/paiements")
    public List<Paiement> getAllPaiements(){
        return paiementRepository.findAll();
    }

    //Create a new Paiement
    @PostMapping(value = "/paiements")
    @ApiOperation(value = "add a Paiement")
    public Paiement createPaiement(@Valid @RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }


    // Get a single paiement
    @ApiOperation(value = "Get a paiement by id")
    @GetMapping(value = "/paiements/{id_paiement}")
    public Paiement getPaiementById(@ApiParam (value = " Paiement Id from which the paiement object will be retrieved", required = true )@PathVariable Long id_paiement) {
        return paiementRepository.findById(id_paiement)
                .orElseThrow(() ->new ResourceNotFoundException("Produit", "idProduit", id_paiement)) ;
    }

    // Update a Paiement
    @ApiOperation(value = "Update a paiement")
    @PutMapping(value = "/paiements/{id_paiement}")
    public Paiement updatePaiement(@ApiParam(value = "Id of the employee to update", required = true)@PathVariable Long id_paiement,
                                 @Valid @RequestBody Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(id_paiement)
                .orElseThrow(() -> new ResourceNotFoundException("id_paiement", "id_paiement", id_paiement));
        paiement.setId_client(paiementDetails.getIdClient());
        paiement.setId_mode_paiement(paiementDetails.getIdMdP());
        paiement.setId_panier(paiementDetails.getIdPanier());
        Paiement updatedPaiement = paiementRepository.save(paiement);
        return updatedPaiement;
    }

    @DeleteMapping (value = "/paiements/{id_paiement}")
    public ResponseEntity<?> deletePaiement(@ApiParam(value = "Paiement Id from which paiementt object will deleted from the database", required = true)@PathVariable Long id_paiement) {
        Paiement paiement = paiementRepository.findById(id_paiement)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement","id_paiement", id_paiement));
        paiementRepository.delete(paiement);
        return ResponseEntity.ok().build();
    }

}