package br.com.zup.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.payment.controller.request.BookRequest;

public interface PaymentApi {

    @PostMapping("/paymenting")
    void book(@RequestBody BookRequest request);
}
