package br.com.zup.order.orchestrator.integration.payment.request;

import java.math.BigDecimal;

public class PaymentRequest {

    private String orderId;

    private BigDecimal amount;

    public PaymentRequest(String orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}