package com.cashmanager.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name= "mode_paiement")

public class PaymentMode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mode_paiement;

    @Column(name = "mode_paiement")
    private String mode_paiement;

    @Column(name = "id_mode_paiement")

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

