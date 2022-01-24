package br.com.zup.order.orchestrator.integration.inventory.request;

import java.util.List;

public class BookRequest {
    private List<BookItemRequest>  orderEntries;

    public List<BookItemRequest> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<BookItemRequest> orderEntries)
    {
        this.orderEntries = orderEntries;
    }
}
