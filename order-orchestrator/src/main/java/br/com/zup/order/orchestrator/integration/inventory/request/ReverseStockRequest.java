package br.com.zup.order.orchestrator.integration.inventory.request;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;

public class ReverseStockRequest {

    private OrderCreatedEvent order;

    public ReverseStockRequest() {
    }

    public ReverseStockRequest(OrderCreatedEvent order) {
        this.order = order;
    }

    public OrderCreatedEvent getOrder() {
        return order;
    }

    public void setOrder(OrderCreatedEvent order) {
        this.order = order;
    }
}
