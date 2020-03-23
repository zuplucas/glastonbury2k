package br.com.zup.order.orchestrator.integration.payment;

import br.com.zup.order.orchestrator.integration.payment.request.PaymentRequest;
import feign.Headers;
import feign.RequestLine;

public interface PaymentApi {

    @RequestLine("POST /payments")
    @Headers("Content-Type: application/json")
    void pay(PaymentRequest request);
}
