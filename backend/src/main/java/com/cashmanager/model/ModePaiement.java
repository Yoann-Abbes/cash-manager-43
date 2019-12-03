package com.cashmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;

@Entity
@Table(name = "modepaiement")
@JavaBean
@ApiModel(description = "All details about the PaymentMode. ")

public class ModePaiement implements Serializable{
    public ModePaiement(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated paymentmode ID")
    private Long id;

    @ApiModelProperty(notes = "The paymentmode name")
    private String mode_paiement;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Paiement.class)
    @ApiModelProperty(notes="The paiement to which the paiementmode is linked")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Paiement paiement;

    public Long getId() {
        return this.id;
    }

    public String getPaymentMode() {
        return this.mode_paiement;
    }
    public Paiement getPaiement() {
        return this.paiement;
    }

    public void setPaiement(Paiement paiement){
        this.paiement=paiement;
    }
    public void setPaymentMode(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }
}
