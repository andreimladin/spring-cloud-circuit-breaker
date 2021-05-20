package com.aimsoft.patterns.circuitbreaker.client.client;

import com.aimsoft.patterns.circuitbreaker.client.model.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="payment", url = "http://localhost:8080/payments")
public interface PaymentClient {

    @PostMapping
    void doPayment(PaymentDTO payment);

}
