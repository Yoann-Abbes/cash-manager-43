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
    public Iterable<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    @GetMapping(value = "/paniers/{panier_id}/produits")
    public Page<Produit> getProduitsbyIdPanier(@PathVariable (value = "panier_id") Long id_panier,
                                               Pageable pageable){
        return produitRepository.findByPanierId(id_panier, pageable);
    }

    // Get a single produit
    @GetMapping(value = "/produits/{idProduit}")
    public Produit getProduitById(@PathVariable(value ="id") Long produit_id ) {
        return produitRepository.findById(produit_id)
                .orElseThrow(() ->new ResourceNotFoundException("Produit", "id", produit_id)) ;
    }



    //Create a new Produit
    @PostMapping(value = "/paniers/{panier_id}/produits")
    public Produit createProduit(@PathVariable (value ="panier_id") Long id_panier,
                                 @Valid @RequestBody Produit produit) {
        return panierRepository.findById(id_panier).map(panier -> {
            produit.setPanier(panier);
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("panier_id", "panier_id", id_panier));
    }


    // Update a Produit
    @PutMapping(value = "/paniers/{panier_id}/produits/{produit_id}")
    public Produit updateProduit(@PathVariable (value="panier_id") Long produit_id,
                                 @PathVariable (value="produit_id")Long panier_id,
                                 @Valid @RequestBody Produit produitDetails) {
        if (!panierRepository.existsById(panier_id)){
            throw new ResourceNotFoundException("Panier", "id", panier_id);
        }
        return produitRepository.findById(produit_id).map(produit -> {
            produit.setName(produitDetails.getName());
            produit.setPrix(produitDetails.getPrix());
            produit.setPanier((produitDetails.getPanier()));
            return produitRepository.save(produit);
        }).orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produit_id ));

    }

    @DeleteMapping (value = "/produits/{produit_id}")
    public ResponseEntity<?> deleteProduit(@PathVariable (value = "produit_id") Long produit_id) {
        return produitRepository.findById(produit_id).map(produit -> {
            produitRepository.delete(produit);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Produit", "id", produit_id));
    }

}