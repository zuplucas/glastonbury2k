package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.UpdateOrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

    @RequestLine("PUT /orders")
    @Headers("Content-Type: application/json")
    void update(UpdateOrderRequest request);
}
