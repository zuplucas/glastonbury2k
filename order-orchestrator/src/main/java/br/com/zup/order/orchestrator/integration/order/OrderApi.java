package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.DeleteOrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

    @RequestLine("DELETE")
    @Headers("Content-Type: application/json")
    void delete(DeleteOrderRequest request);
}
