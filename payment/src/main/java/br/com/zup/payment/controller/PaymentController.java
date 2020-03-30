package br.com.zup.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.payment.request.PaymentRequest;
import br.com.zup.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    private PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void pay(@RequestBody PaymentRequest request) {
        paymentService.pay(request);
    }

}
