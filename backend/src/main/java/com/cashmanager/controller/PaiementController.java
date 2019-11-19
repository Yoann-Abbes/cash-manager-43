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



    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    //Get all Paiements
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
    @GetMapping(value = "/paiements/{idPaiement}")
    public Paiement getPaiementById(@ApiParam (value = " Paiement Id from which the paiement object will be retrieved", required = true )@PathVariable Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() ->new ResourceNotFoundException("Paiement", "idPaiement", idPaiement)) ;
    }

    // Update a Paiement
    @ApiOperation(value = "Update a paiement")
    @PutMapping(value = "/paiements/{idPaiement}")
    public Paiement updatePaiement(@ApiParam(value = "Id of the employee to update", required = true)@PathVariable Long idPaiement,
                                 @Valid @RequestBody Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new ResourceNotFoundException("idPaiement", "idPaiement", idPaiement));
        paiement.setIdClient(paiementDetails.getIdClient());
        paiement.setIdModePaiement(paiementDetails.getIdMdP());
        paiement.setIdPanier(paiementDetails.getIdPanier());
        Paiement updatedPaiement = paiementRepository.save(paiement);
        return updatedPaiement;
    }

    @DeleteMapping (value = "/paiements/{idPaiement}")
    public ResponseEntity<?> deletePaiement(@ApiParam(value = "Paiement Id from which paiement object will deleted from the database", required = true)@PathVariable Long idPaiement) {
        Paiement paiement = paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement","id_paiement", idPaiement));
        paiementRepository.delete(paiement);
        return ResponseEntity.ok().build();
    }

}