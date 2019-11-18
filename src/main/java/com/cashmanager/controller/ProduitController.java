package com.cashmanager.controller;

import com.cashmanager.model.Panier;
import com.cashmanager.model.Produit;
import com.cashmanager.repository.PanierRepository;
import com.cashmanager.repository.ProduitRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private PanierRepository panierRepository;

    //Get all Produits
    @GetMapping(value = "/produits")
    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    @GetMapping(value = "/produits")
    public Page<Produit> getProduitsbyIdPanier(@PathVariable (value = "idPanier") Long idPanier,
                                               Pageable pageable){
        return produitRepository.findByPanierId(idPanier, pageable);
    }
    //Create a new Produit
    @PostMapping(value = "/produits")
    public Produit createProduit(@PathVariable (value ="idPanier") Long idPanier,
                                 @Valid @RequestBody Produit produit) {
        return panierRepository.findById(idPanier).map(panier -> {
            produit.setPanier(panier);
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("idPanier", "idPanier", idPanier));
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
                                 @PathVariable Long idPanier,
                                 @Valid @RequestBody Produit produitDetails) {
        if (!panierRepository.existsById(idPanier)){
                 throw new ResourceNotFoundException("Panier", "idPanier", idPanier);
        }
        return produitRepository.findById(idProduit).map(produit -> {
            produit.setName(produitDetails.getName());
            produit.setPrix(produitDetails.getPrix());
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("Produit", "idProduit", idProduit ));

    }

    @DeleteMapping (value = "/produits/{idProduit}")
    public ResponseEntity<?> deleteProduit(@PathVariable (value = "idPanier") Long idPanier,
                                           @PathVariable (value = "idProduit") Long idProduit) {
        return produitRepository.findByIdAndPanierId(idProduit, idPanier).map(produit -> {
            produitRepository.delete(produit);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Produit", "idProduit ", idProduit));
    }

}