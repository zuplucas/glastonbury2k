package br.com.zup.order.orchestrator.integration.payment;

import feign.Headers;
import feign.RequestLine;

public interface PaymentApi {

    @RequestLine("POST /pay")
    @Headers("Content-Type: application/json")
    boolean  payment();
}
