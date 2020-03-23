package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import br.com.zup.order.orchestrator.integration.inventory.request.UndoBookRequest;
import feign.Headers;
import feign.RequestLine;

public interface InventoryApi {

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    void book(BookRequest request);

    @RequestLine("DELETE /booking")
    @Headers("Content-Type: application/json")
    void undo(UndoBookRequest request);
}
