package com.driver.controllers;

import com.driver.models.Payment;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Payment pay(@RequestParam Integer reservationId, @RequestParam Integer amountSent, @RequestParam String mode) throws Exception {
        // Validate payment mode
        if (!isValidPaymentMode(mode)) {
            throw new Exception("Payment mode not detected");
        }

        // Check if the amount sent is sufficient
        if (!paymentService.isAmountSufficient(reservationId, amountSent)) {
            throw new Exception("Insufficient Amount");
        }

        // Update payment attributes for the reservation
        return paymentService.processPayment(reservationId, amountSent, mode);
    }

    // Validate payment mode
    private boolean isValidPaymentMode(String mode) {
        return mode.equalsIgnoreCase("cash") || mode.equalsIgnoreCase("card") || mode.equalsIgnoreCase("upi");
    }
}
