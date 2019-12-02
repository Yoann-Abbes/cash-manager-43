package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Long idPaiement;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Panier.class)
    @ApiModelProperty(notes="The panier to which the paiement is linked")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Panier panier;

    public Long getId_paiement() { return this.idPaiement; }

    public Panier getPanier() { return this.panier; }
    public void setPanier(Panier panier) {
        this.panier =  panier;
    }


}
