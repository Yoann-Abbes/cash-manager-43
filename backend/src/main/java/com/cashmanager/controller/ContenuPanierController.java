package com.cashmanager.controller;

import com.cashmanager.exception.ResourceNotFoundException;

import com.cashmanager.model.ContenuPanier;
import com.cashmanager.model.Produit;
import com.cashmanager.repository.ContenuPanierRepository;
import com.cashmanager.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

public class ContenuPanierController {

    @Autowired
    private ContenuPanierRepository contenuPanierRepository;

    @Autowired
    private PanierRepository panierRepository;

    @GetMapping(value = "/contenupaniers")
    public Iterable<ContenuPanier> getAllContenus() {
        return contenuPanierRepository.findAll();
    }
    // Get a single produit
    @GetMapping(value = "/contenupaniers/{id}")
    public ContenuPanier getProduitById(@PathVariable(value ="id") Long contenu_id ) {
        return contenuPanierRepository.findById(contenu_id)
                .orElseThrow(() ->new ResourceNotFoundException("ContenuPanier", "id", contenu_id)) ;
    }

    @GetMapping(value ="/paniers/{panier_id}/contenupaniers")
    public Page<ContenuPanier> getContenuByPanierId(@PathVariable (value = "panier_id") Long panier_id,
                                                    Pageable pageable){
        return contenuPanierRepository.findByCartId(panier_id,pageable);

    }
    @PostMapping("/paniers/{panier_id/produit_id/contenupaniers")
    public ContenuPanier createContenuPanier(@PathVariable (value = "panier_id") Long panier_id,
                                         @PathVariable (value = "produit_id") Long produit_id,
                                         @Valid @RequestBody ContenuPanier contenuPanier) {
        Product product = productService.getProductById(productId);
        return cartRepository.findById(cartId).map(cart -> {
            cartContent.setCart(cart);
            cartContent.setProduct(product);
            return cartContentRepository.save(cartContent);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }

}
