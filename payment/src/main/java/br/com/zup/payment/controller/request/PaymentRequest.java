package br.com.zup.payment.controller.request;

import br.com.zup.payment.event.OrderCreatedEvent;

public class PaymentRequest {
    private OrderCreatedEvent order;

    public OrderCreatedEvent getOrder() {
        return order;
    }

    public void setOrder(OrderCreatedEvent order) {
        this.order = order;
    }
}
