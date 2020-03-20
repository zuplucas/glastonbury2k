package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import br.com.zup.order.orchestrator.integration.inventory.request.UnbookRequest;
import feign.Headers;
import feign.RequestLine;

public interface InventoryApi {

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    void book(BookRequest request);

    @RequestLine("POST /unbooking")
    @Headers("Content-Type: application/json")
    void unbook(UnbookRequest request);
}
