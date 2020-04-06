package br.com.zup.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface PaymentApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean payment() throws Exception;



}
