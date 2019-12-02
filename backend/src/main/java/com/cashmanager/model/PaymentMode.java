package com.cashmanager.model;
import javax.persistence.*;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;

@Entity
@Table(name= "mode_paiement")
@JavaBean
@ApiModel(description = "All details about the PaymentMode. ")

public class PaymentMode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated paymentmode ID")
    private Long id_mode_paiement;

    @Column(name = "mode_paiement")
    @ApiModelProperty(notes = "The paymentmode name")
    private String mode_paiement;

    @Column(name = "id_mode_paiement")
    @ApiModelProperty(notes = "The paymentmode id")
    public Long getId() {
        return this.id_mode_paiement;
    }

    public String getPaymentMode() {
        return this.mode_paiement;
    }

    public void setPaymentMode(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }


}
