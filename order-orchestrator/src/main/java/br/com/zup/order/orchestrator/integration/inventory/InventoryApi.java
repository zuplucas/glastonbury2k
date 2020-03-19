package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import br.com.zup.order.orchestrator.integration.inventory.request.ReverseStockRequest;
import feign.Headers;
import feign.RequestLine;

public interface InventoryApi {

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    void book(BookRequest request);

    @RequestLine("POST /reverse")
    @Headers("Content-Type: application/json")
    void reverseStock(ReverseStockRequest request);
}
