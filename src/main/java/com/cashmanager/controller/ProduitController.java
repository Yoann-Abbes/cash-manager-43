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
    @GetMapping(value = "/produits/{idProduit}")
    public Produit getProduitById(@PathVariable Long idProduit ) {
        return produitRepository.findById(idProduit)
                .orElseThrow(() ->new ResourceNotFoundException("Produit", "idProduit", idProduit)) ;
    }

    // Update a Produit
    @PutMapping(value = "/produits/{idProduit}")
    public Produit updateProduit(@PathVariable Long idProduit,
                                 @Valid @RequestBody Produit produitDetails) {
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "idProduit", idProduit));
        produit.setName(produitDetails.getName());
        produit.setPrix(produitDetails.getPrix());
        Produit updatedProduit = produitRepository.save(produit);
        return updatedProduit;
    }

    @DeleteMapping (value = "/produits/{idProduit}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long idProduit) {
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit","idProduit", idProduit));
        produitRepository.delete(produit);
        return ResponseEntity.ok().build();
    }

}