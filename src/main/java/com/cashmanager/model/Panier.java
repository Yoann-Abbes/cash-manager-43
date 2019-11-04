package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;

@Entity
@Table(name="Panier")
@JavaBean
@ApiModel(description = "All details about the Panier")

public class Panier implements Serializable {
    @Id
    @ApiModelProperty(notes="The Panier id")
    @Column(name="id_panier")
    private Long id_panier;

    @Column(name="id_client")
    @ApiModelProperty(notes="The client owning the Panier")
    private Long id_client;

    @Column(name="id_produit")
    @ApiModelProperty(notes="A Produit in the Panier")
    private Long id_produit;


    // GET and SET
    public Long getIdPanier(){
        return this.id_panier;
    }
    public void setIdPanier(Long id_panier){
        this.id_panier = id_panier;
    }


    public Long getIdClient(){
        return this.id_client;
    }
    public void setIdClient(Long id_client){
        this.id_client = id_client;
    }


    public Long getIdProduit() {
        return  this.id_produit;
    }
    public void setIdProduit(Long id_produit){
        this.id_produit= id_produit;
    }

}
