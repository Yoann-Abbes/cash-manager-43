package com.cashmanager.controller;
import com.cashmanager.model.Client;
import com.cashmanager.model.PaymentMode;
import com.cashmanager.repository.PaymentModeRepository;
import com.cashmanager.exception.ResourceNotFoundException;
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

}
