package br.com.zup.payment.controller;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController implements PaymentApi {

    private static final String BOOK_NOT_SUCCESS = "book-not-success";
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void pay(@RequestBody PaymentRequest request) {
        paymentService.pay(request.getOrder());
    }
}
