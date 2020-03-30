package br.com.zup.payment.request;

import java.math.BigDecimal;

public class PaymentRequest {

    private BigDecimal amount;
    private String orderId;

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentRequest(BigDecimal amount, String orderId) {
        this.amount = amount;
        this.orderId = orderId;
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
