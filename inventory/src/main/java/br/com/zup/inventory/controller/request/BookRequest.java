package br.com.zup.inventory.controller.request;

import br.com.zup.inventory.event.OrderCreatedItemEvent;

import java.util.List;

public class BookRequest {
    private List<OrderCreatedItemEvent> orderEntries;

    public List<OrderCreatedItemEvent> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderCreatedItemEvent> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
