package com.cashmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "produit")
@JavaBean
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Lob @Type(type="org.hibernate.type.BlobType")
//    private Blob image;

    @Column(name = "id_produit")
    private long idProduit;

    @Column(name = "name")
    private String name;

    @Column(name = "prix")
    private double prix;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Panier panier;

    public long getId() {
        return this.idProduit;
    }

    public double getPrix() { return this.prix; }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Panier getPanier(){
        return this.panier;
    }
    public void setPanier(Panier panier) { this.panier=panier;}
}