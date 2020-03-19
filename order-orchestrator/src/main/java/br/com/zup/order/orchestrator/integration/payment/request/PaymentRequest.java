package br.com.zup.order.orchestrator.integration.payment.request;


import br.com.zup.order.orchestrator.event.OrderCreatedEvent;

public class PaymentRequest {
    private OrderCreatedEvent order;

    public PaymentRequest(OrderCreatedEvent order) {
        this.order = order;
    }

    public OrderCreatedEvent getOrder() {
        return order;
    }

    public void setOrder(OrderCreatedEvent order) {
        this.order = order;
    }
}
