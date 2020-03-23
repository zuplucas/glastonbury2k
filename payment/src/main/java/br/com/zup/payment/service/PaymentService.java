package br.com.zup.payment.service;

import br.com.zup.payment.controller.request.PaymentRequest;

public interface PaymentService {

    void pay(PaymentRequest paymentRequest);
}
