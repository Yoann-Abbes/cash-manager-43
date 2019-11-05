package com.cashmanager.controller;
import com.cashmanager.model.Client;

import com.cashmanager.model.PaymentMode;
import com.cashmanager.repository.PaymentModeRepository;
import com.cashmanager.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.ElementCollection;
import javax.validation.Valid;
import java.util.List;

@RestController

public class PaymentModeController {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    // Get all the payment methods
    @GetMapping(value = "/paymentmode")

    public List<PaymentMode> getPaymentMode() {
        return paymentModeRepository.findAll();
    }

    // Get the payment method by id
    @GetMapping(value = "/paymentmode/{idPaymentmode}")
    public PaymentMode getPaymentModeById(@PathVariable Long idPaymentmode) {
        return paymentModeRepository.findById(idPaymentmode)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMode", "idPaymentMode", idPaymentmode));
    }

    // create a new payment mode
    @PostMapping(value = "/paymentmode")
    public PaymentMode createPaymentMode(@Valid @RequestBody PaymentMode paymentMode) {
        return paymentModeRepository.save(paymentMode);
    }

    // modify a payment mode
    @PutMapping(value = "/paymentmode/{idPaymentmode}")
    public PaymentMode updatePaymentMode(@PathVariable Long idPaymentmode,
                                         @Valid @RequestBody PaymentMode paymentModeDetails) {
        PaymentMode paymentMode = paymentModeRepository.findById(idPaymentmode)
                .orElseThrow(() -> new ResourceNotFoundException("Payment mode", "idPaymentmode", idPaymentmode));

        paymentMode.setPaymentMode(paymentModeDetails.getPaymentMode());
        PaymentMode updatedPaymentMode = paymentModeRepository.save(paymentMode);
        return updatedPaymentMode;
    }

    // delete a payment mode
    @DeleteMapping (value = "/paymentmode/{idPaymentmode}")
    public ResponseEntity<?> detelePaymentMode(@PathVariable Long idPaymentmode) {
        PaymentMode paymentMode = paymentModeRepository.findById(idPaymentmode)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMode","idPaymentmode", idPaymentmode));
        paymentModeRepository.delete(paymentMode);
        return ResponseEntity.ok().build();
    }
}
