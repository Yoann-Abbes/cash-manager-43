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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="The Panier id")
    private Long idPanier;

    @Column(name="idClient")
    @ApiModelProperty(notes="The client owning the Panier")
    private Long idClient;

    @Column(name="idProduit")
    @ApiModelProperty(notes="A Produit in the Panier")
    private Long idProduit;


    // GET and SET
    public Long getIdPanier(){
        return this.idPanier;
    }
    public void setIdPanier(Long idPanier){
        this.idPanier = idPanier;
    }


    public Long getIdClient(){
        return this.idClient;
    }
    public void setIdClient(Long idClient){
        this.idClient = idClient;
    }


    public Long getIdProduit() {
        return  this.idProduit;
    }
    public void setIdProduit(Long idProduit){
        this.idProduit= idProduit;
    }

}
