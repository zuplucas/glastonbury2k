package br.com.zup.payment.event;

import java.math.BigDecimal;

public class PaymentEvent {

    private String orderId;
    private BigDecimal amount;

    public PaymentEvent() {
    }

    public PaymentEvent(String orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
