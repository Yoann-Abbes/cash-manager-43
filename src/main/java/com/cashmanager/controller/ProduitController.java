package com.cashmanager.controller;

import com.cashmanager.model.Produit;
import com.cashmanager.repository.ProduitRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;

    //Get all Produits
    @GetMapping("/produits")
    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    //Create a new Produit
    @PostMapping("/produits")
    public Produit createProduit(@Valid @ RequestBody Produit produit) {
        return produitRepository.save(produit);
    }


    // Get a single produit
    @GetMapping("/produits/{id}")
    public Produit getProduitById(@PathVariable(value = "idProduit") Long produitId ) {
        return produitRepository.findById(produitId)
                .orElseThrow(() ->new ResourceNotFoundException("Produit", "idProduit", produitId)) ;
    }

    // Update a Produit
    @PutMapping("/produits/{id]")
    public Produit updateProduit(@PathVariable(value = "idProduit") Long produitId,
                                 @Valid @RequestBody Produit produitDetails) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produitId));
        produit.setName(produitDetails.getName());
        produit.setPrix(produitDetails.getPrix());
        Produit updatedProduit = produitRepository.save(produit);
        return updatedProduit;
    }

    @DeleteMapping ("/produits/{id]")
    public ResponseEntity<?> deleteProduit(@PathVariable(value = "idProduit") Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",produitId));
        produitRepository.delete(produit);
        return ResponseEntity.ok().build();
    }

}