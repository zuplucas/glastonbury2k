package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.StatusOrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface StatusOrderApi {

    @RequestLine("POST /statusorder")
    @Headers("Content-Type: application/json")
    void atualizar(StatusOrderRequest request);
}
