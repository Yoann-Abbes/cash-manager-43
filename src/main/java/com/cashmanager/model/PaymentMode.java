package com.cashmanager.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name= "mode_paiement")

public class PaymentMode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaymentMode;

    @Column(name = "paymentMode")
    private String paymentMode;

    public String getMode() {
        return this.paymentMode;
    }
    public void setpaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}

