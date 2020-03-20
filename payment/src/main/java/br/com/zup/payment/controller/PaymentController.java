package br.com.zup.payment.controller;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/pay")
    public void pay(@RequestBody PaymentRequest paymentRequest) {
        this.paymentService.process(paymentRequest);
    }
}
