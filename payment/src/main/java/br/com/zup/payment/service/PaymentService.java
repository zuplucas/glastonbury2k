package br.com.zup.payment.service;

import br.com.zup.payment.controller.request.BookRequest;

public interface PaymentService {

    String payment( BookRequest request);

}
