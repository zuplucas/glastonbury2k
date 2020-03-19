package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.UpdateStatusOrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

    @RequestLine("POST /status")
    @Headers("Content-Type: application/json")
    void updateStatus(UpdateStatusOrderRequest request);
}
