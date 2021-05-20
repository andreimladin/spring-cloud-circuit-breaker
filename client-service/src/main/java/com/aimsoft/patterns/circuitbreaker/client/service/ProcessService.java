package com.aimsoft.patterns.circuitbreaker.client.service;

import com.aimsoft.patterns.circuitbreaker.client.client.PaymentClient;
import com.aimsoft.patterns.circuitbreaker.client.model.dto.PaymentDTO;
import com.aimsoft.patterns.circuitbreaker.client.model.dto.ProcessDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    @Autowired
    private PaymentClient paymentClient;

    @HystrixCommand(fallbackMethod = "paymentFailure",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10")
            },
            threadPoolProperties = {
                @HystrixProperty(name="keepAliveTimeMinutes", value = "1"),
                @HystrixProperty(name="queueSizeRejectionThreshold", value = "3")
            }
    )
    public void add(ProcessDTO process) {
        PaymentDTO payment = new PaymentDTO();
        payment.setAmount(1000);
        payment.setDecimals(2);
        if (process.getDuration() > 10) {
            payment.setCurrency("EUR");
        } else if (process.getDuration() < 10) {
            payment.setCurrency("GBP");
        } else {
            payment.setCurrency("RON");
        }

        paymentClient.doPayment(payment);
    }

    public void paymentFailure(ProcessDTO process) {
        System.out.println("Payment Failure");
    }
}
