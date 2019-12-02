package com.cashmanager.controller;

import com.cashmanager.exception.ResourceNotFoundException;

import com.cashmanager.model.ContenuPanier;
import com.cashmanager.model.Panier;
import com.cashmanager.model.Produit;
import com.cashmanager.repository.ContenuPanierRepository;
import com.cashmanager.repository.PanierRepository;
import com.cashmanager.repository.ProduitRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class ContenuPanierController {

    @Autowired
    private ContenuPanierRepository contenuPanierRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private PanierRepository panierRepository;

    @ApiOperation(value = "View a list of ContenuPanier", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/contenupaniers")
    public Iterable<ContenuPanier> getAllContenus() {
        return contenuPanierRepository.findAll();
    }

    // Get a single produit
    @ApiOperation(value="Add a contenupanier")
    @GetMapping(value = "/contenupaniers/{id}")
    public ContenuPanier getProduitById(@PathVariable(value ="id") Long contenu_id ) {
        return contenuPanierRepository.findById(contenu_id)
                .orElseThrow(() ->new ResourceNotFoundException("ContenuPanier", "id", contenu_id)) ;
    }

    @ApiOperation(value = "Get a single contenupanier by its id")
    @GetMapping(value ="/paniers/{panier_id}/contenupaniers")
    public Page<ContenuPanier> getContenuByPanierId(@PathVariable (value = "panier_id") Long panier_id,
                                                    Pageable pageable){
        return contenuPanierRepository.findByPanier_id(panier_id,pageable);

    }
    @ApiOperation(value = "Post a contenupanier with a Panier id and a Produit id ")
    @PostMapping(value = "/paniers/{panier_id}/produits/{produit_id}/contenupaniers")
    public ContenuPanier createContenuPanier(@PathVariable (value = "panier_id") Long panier_id,
                                         @PathVariable (value = "produit_id") Long produit_id,
                                         @Valid @RequestBody ContenuPanier contenuPanier) {
        return panierRepository.findById(panier_id).map(panier -> {
            Produit produit = produitRepository.getOne(produit_id);
            panier.setTotal(panier.getTotal() + contenuPanier.getQuantite() * produit.getPrix());
            panier.setQuantite(panier.getQuantite() + contenuPanier.getQuantite());
            contenuPanier.setPanier(panier);
            contenuPanier.setProduit(produit);
            panierRepository.save(panier);
            return contenuPanierRepository.save(contenuPanier);
        }).orElseThrow(() -> new ResourceNotFoundException("Panier", "id", panier_id));
    }

    @ApiOperation(value = "Updating a Contenupanier with the panier_id to which it's linked")
    @PutMapping("paniers/{panier_id}/contenupaniers/{id}")
    public ContenuPanier updateContenuPanier(@PathVariable (value = "id") Long contenupanier_id,
                                         @PathVariable (value = "panier_id") Long panier_id,
                                         @Valid @RequestBody ContenuPanier contenuPanierDetails ){
        if(!panierRepository.existsById(panier_id)) {
            throw new ResourceNotFoundException("Panier", "id", panier_id);
        }
        return contenuPanierRepository.findById(contenupanier_id).map(contenuPanier -> {
            contenuPanier.setQuantite(contenuPanierDetails.getQuantite());
            contenuPanier.setPanier(panierRepository.getOne(panier_id));
            Panier panier = panierRepository.getOne((panier_id));
            panier.setQuantite(panier.getQuantite() + contenuPanierDetails.getQuantite());
            panier.setTotal(panier.getTotal() + contenuPanierDetails.getQuantite() * contenuPanier.getProduit().getPrix());
            panierRepository.save(panier);
            return contenuPanierRepository.save(contenuPanier);
        }).orElseThrow(() -> new ResourceNotFoundException("ContenuPanier", "id", contenupanier_id));
    }
    @ApiOperation(value = "Deleting a contenupanier with its id and the id of the panier it's linked to")
    @DeleteMapping("paniers/{panier_id}/contenupaniers/{id}")
    public ResponseEntity<?> deleteCartContent(@PathVariable (value = "panier_id") Long panier_id,
                                               @PathVariable (value = "id") Long contenupanier_id) {
        return contenuPanierRepository.findByIdAndPanier_id(contenupanier_id, panier_id).map(contenuPanier -> {

            Panier panier = panierRepository.getOne((panier_id));
            panier.setQuantite(panier.getQuantite() - contenuPanier.getQuantite());
            panier.setTotal(panier.getTotal() - contenuPanier.getQuantite() * contenuPanier.getProduit().getPrix());
            panierRepository.save(panier);

            contenuPanierRepository.delete(contenuPanier);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ContenuPanier", "id", contenupanier_id));
    }




}
