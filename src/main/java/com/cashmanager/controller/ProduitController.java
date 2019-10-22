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

@RestController
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    //Get all Produits
    @GetMapping(value = "/produits")
    public List<Produit> getAllProduits(){
        System.out.println("=================================");
        return produitRepository.findAll();
    }

    //Create a new Produit
    @PostMapping(value = "/produits")
    public Produit createProduit(@Valid @RequestBody Produit produit) {
        return produitRepository.save(produit);
    }


    // Get a single produit
    @GetMapping(value = "/produits/{id}")
    public Produit getProduitById(@PathVariable Long produitId ) {
        return produitRepository.findById(produitId)
                .orElseThrow(() ->new ResourceNotFoundException("Produit", "idProduit", produitId)) ;
    }

    // Update a Produit
    @PutMapping(value = "/produits/{id]")
    public Produit updateProduit(@PathVariable Long produitId,
                                 @Valid @RequestBody Produit produitDetails) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "idProduit", produitId));
        produit.setName(produitDetails.getName());
        produit.setPrix(produitDetails.getPrix());
        Produit updatedProduit = produitRepository.save(produit);
        return updatedProduit;
    }

    @DeleteMapping (value = "/produits/{id]")
    public ResponseEntity<?> deleteProduit(@PathVariable Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit","idProduit", produitId));
        produitRepository.delete(produit);
        return ResponseEntity.ok().build();
    }

}