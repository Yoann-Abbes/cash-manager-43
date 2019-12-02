package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;

@Entity
@Table(name = "paiement")
@JavaBean
//@JsonIgnoreProperties(allowGetters = true)
@ApiModel(description="All details about the Paiement")

public class Paiement implements Serializable {
    public Paiement() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated paiement ID")
    private int idPaiement;

    //@Column(name = "id_client")
    @NotBlank
    @ApiModelProperty(notes = "The Client linked to the Paiement")
    private int idClient;

    //@Column(name = "id_mode_paiement")
    @NotBlank
    @ApiModelProperty(notes = "The MdP linked to the Paiement")
    private int idModePaiement;

    @Column(name = "id_panier")
    @ApiModelProperty(notes = "The Panier containing the Paiement")
    private int idPanier;

    public int getId_paiement() { return this.idPaiement; }

    public int getIdClient() { return this.idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public int getIdMdP() {
        return this.idModePaiement;
    }
    public void setIdModePaiement(int idModePaiement) {  this.idModePaiement = idModePaiement; }

    public int getIdPanier() { return this.idPanier; }
    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }


}
