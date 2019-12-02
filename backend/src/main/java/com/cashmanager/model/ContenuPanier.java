package com.cashmanager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "contenupanier")
@JsonIgnoreProperties(allowGetters = true)

public class ContenuPanier implements Serializable {
    public ContenuPanier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="produit_id")
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "panier_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Panier panier;

    private int quantite;

    public Long getId() { return this.id; }

    public Produit getProduit() { return this.produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public Panier getPanier() { return this.panier; }
    public void setPanier(Panier panier) { this.panier = panier; }

    public int getQuantite() { return this.quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
