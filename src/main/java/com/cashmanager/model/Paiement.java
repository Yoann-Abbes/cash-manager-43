package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;

@Entity
@Table(name = "paiement")
@JavaBean
//@JsonIgnoreProperties(allowGetters = true)

public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated paiement ID")
    private int id_paiement;

    @Column(name = "id_client")
    @ApiModelProperty(notes = "The Client linked to the Paiement")
    private int id_client;

    @Column(name = "id_mode_paiement")
    @ApiModelProperty(notes = "The MdP linked to the Paiement")
    private int id_mode_paiement;

    @Column(name = "id_panier")
    @ApiModelProperty(notes = "The Panier containing the Paiement")
    private int id_panier;

    public int getId_paiement() { return this.id_paiement; }

    public int getIdClient() { return this.id_client; }
    public void setId_client(int id_client) { this.id_client = id_client; }

    public int getIdMdP() {
        return this.id_mode_paiement;
    }
    public void setId_mode_paiement(int id_mode_paiement) {  this.id_mode_paiement = id_mode_paiement; }

    public int getIdPanier() { return this.id_panier; }
    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }


}
