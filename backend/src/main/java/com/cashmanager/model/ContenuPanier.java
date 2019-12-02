package com.cashmanager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "contenupanier")
@JsonIgnoreProperties(allowGetters = true)
@ApiModel(description="Liaison between a Panier and a Produit")
public class ContenuPanier implements Serializable {
    public ContenuPanier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Database generaged paniercontenuID")
    private Long id;

    @OneToOne(targetEntity = Produit.class)
    @JoinColumn(name="produit_id")
    @ApiModelProperty(notes="A produit to be linked to a panier")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Panier.class)
    @JoinColumn(name = "panier_id")
    @ApiModelProperty(notes="A panier to which we want to add a produit")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Panier panier;

    @ApiModelProperty(notes="Quantity of a certain produits in a panier")
    private int quantite;

    public Long getId() { return this.id; }

    public Produit getProduit() { return this.produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public Panier getPanier() { return this.panier; }
    public void setPanier(Panier panier) { this.panier = panier; }

    public int getQuantite() { return this.quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
