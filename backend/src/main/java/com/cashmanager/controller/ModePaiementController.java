package com.cashmanager.controller;

import com.cashmanager.exception.ResourceNotFoundException;
import com.cashmanager.model.ModePaiement;
import com.cashmanager.model.Paiement;
import com.cashmanager.repository.ModePaiementRepository;
import com.cashmanager.repository.PaiementRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "PaymentMode Controller")

public class ModePaiementController {

    @Autowired
    private ModePaiementRepository modePaiementRepository;

    @Autowired
    private PaiementRepository paiementRepository;
    // Get All Clients
    @ApiOperation(value = "View a list of available payment modes", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    // Get all the payment methods
    @GetMapping(value = "/modepaiements")

    public List<ModePaiement> getModePaiement() {
        return modePaiementRepository.findAll();
    }

    // Get the payment method by id
    @GetMapping(value = "/modepaiements/{id}")
    @ApiOperation(value = "Get an payment mode")
    public ModePaiement getModePaiementById(@PathVariable (value = "id" )Long id) {
        return modePaiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ModePaiement", "id", id));
    }

    // create a new payment mode
    @PostMapping(value = "/paiements/{paiement_id}/modepaiements")
    @ApiOperation(value = "Add a new payment mode")
    public ModePaiement createPaymentMode(@Valid @RequestBody ModePaiement modePaiement,
                                          @PathVariable (value = "paiement_id") Long paiement_id) {
        Paiement paiement = paiementRepository.findById(paiement_id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement","paiement_id", paiement_id));
        modePaiement.setPaiement(paiement);
        return modePaiementRepository.save(modePaiement);
    }

    // modify a payment mode
    @PutMapping(value = "/modepaiements/{id}")
    @ApiOperation(value = "Update a payment mode")
    public ModePaiement updateModePaiement(@PathVariable (value = "id") Long id,
                                         @Valid @RequestBody ModePaiement modePaiementDetails) {
        ModePaiement modePaiement = modePaiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ModePaiement", "id", id));

        modePaiement.setPaymentMode(modePaiementDetails.getPaymentMode());
        ModePaiement updatedPaymentMode = modePaiementRepository.save(modePaiement);
        return updatedPaymentMode;
    }

    // delete a payment mode
    @DeleteMapping (value = "/modepaiements/{id}")
    @ApiOperation(value = "delete a payment mode")
    public ResponseEntity<?> detelePaymentMode(@PathVariable(value = "id") Long id) {
        ModePaiement modePaiement = modePaiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ModePaiement","id", id));
        modePaiementRepository.delete(modePaiement);
        return ResponseEntity.ok().build();
    }
}