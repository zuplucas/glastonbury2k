package br.com.zup.order.orchestrator.integration.inventory.request;


import br.com.zup.order.orchestrator.event.OrderCreatedItemEvent;

import java.util.List;

public class BookRequest {
    private String orderId;
    private List<OrderCreatedItemEvent> orderEntries;

    public BookRequest(String orderId, List<OrderCreatedItemEvent> orderEntries) {
        this.orderId = orderId;
        this.orderEntries = orderEntries;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderCreatedItemEvent> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderCreatedItemEvent> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
