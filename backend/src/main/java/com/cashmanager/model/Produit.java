package com.cashmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.JavaBean;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "produit")
@JsonIgnoreProperties(allowGetters = true)

public class Produit implements Serializable {

    public Produit() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @NotNull
    private double prix;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    public Long getId() { return this.id; }

    public double getPrix() { return this.prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Panier getPanier(){return this.panier;}
    public void setPanier(Panier panier) { this.panier=panier;}
}