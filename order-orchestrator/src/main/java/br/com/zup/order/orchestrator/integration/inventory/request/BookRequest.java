package br.com.zup.order.orchestrator.integration.inventory.request;

import java.util.List;

public class BookRequest {

    private String orderId;
    private List<BookItemRequest> entries;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<BookItemRequest> getEntries() {
        return entries;
    }

    public void setEntries(List<BookItemRequest> entries) {
        this.entries = entries;
    }
}
