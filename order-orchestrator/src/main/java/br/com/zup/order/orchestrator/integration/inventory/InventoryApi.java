package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface InventoryApi {

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    void book(BookRequest request);

    @RequestLine("PATCH /unbook/{orderId}")
    void unbookTickets(@Param("orderId") String orderId);
}
