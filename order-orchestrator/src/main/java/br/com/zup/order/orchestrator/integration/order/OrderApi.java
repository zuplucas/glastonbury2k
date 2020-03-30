package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OrderApi {

    @RequestLine("PATCH /cancel-order/{orderId}")
    void cancel(@Param("orderId") String orderId);

    @RequestLine("PATCH /complete/{orderId}")
    void complete(@Param("orderId") String orderId);

    @RequestLine("PATCH /reject/{orderId}")
    void rejectOrder(@Param("orderId") String orderId);
}
