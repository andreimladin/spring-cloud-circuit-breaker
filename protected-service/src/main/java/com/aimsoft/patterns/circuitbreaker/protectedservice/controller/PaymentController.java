package com.aimsoft.patterns.circuitbreaker.protectedservice.controller;

import com.aimsoft.patterns.circuitbreaker.protectedservice.controller.dto.PaymentDTO;
import com.aimsoft.patterns.circuitbreaker.protectedservice.exception.CurrencyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public void doPayment(@RequestBody PaymentDTO payment) {
        if ("EUR".equals(payment.getCurrency())) {
            throw new RuntimeException();
        } else if ("GBP".equals(payment.getCurrency())) {
            throw new CurrencyNotFoundException();
        } else {
            System.out.println("Payment has been successfully debited");
        }
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void internalError(CurrencyNotFoundException e) {
        System.out.println("Currency not found");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void internalError(RuntimeException e) {
        System.out.println("Internal error");
    }


}
