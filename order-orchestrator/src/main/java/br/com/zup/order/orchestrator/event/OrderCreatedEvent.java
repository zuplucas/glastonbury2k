package br.com.zup.order.orchestrator.event;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private List<OrderItemPartEvent> orderItems;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, List<OrderItemPartEvent> orderItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<OrderItemPartEvent> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemPartEvent> orderItems) {
        this.orderItems = orderItems;
    }
}
