package br.com.zup.order.orchestrator.event.payment;

import br.com.zup.order.orchestrator.event.order.OrderItemCreatedEvent;

import java.math.BigDecimal;
import java.util.List;

public class PaymentEvent {

    private String orderId;
    private boolean status;


    public PaymentEvent() {
    }

    public PaymentEvent(String orderId, boolean status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
