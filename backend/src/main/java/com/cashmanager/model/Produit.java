package com.cashmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "All details about a produit ")
public class Produit implements Serializable {

    public Produit() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Database generated Produit id")
    private Long id;

    @Column
    @NotBlank
    @ApiModelProperty(notes = "name of the produit")
    private String name;

    @NotNull
    @ApiModelProperty(notes = "Produits's price")
    private double prix;

    public Long getId() { return this.id; }

    public double getPrix() { return this.prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}