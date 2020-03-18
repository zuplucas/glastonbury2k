package br.com.zup.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.payment.controller.request.PaymentRequest;

@RestController
@RequestMapping("/payment")
public class PaymentController{

    @Autowired
    public PaymentController() {
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void pay(@RequestBody PaymentRequest request) {
    	System.out.println(request.getOrderId());
        System.out.println(request.getOrderEntries().toString());
    }
}
