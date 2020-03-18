package br.com.zup.order.orchestrator.event;

import java.math.BigDecimal;
import java.util.Map;

public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private Map<String, String> items;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, Map<String, String> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.items = items;
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

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }
}
