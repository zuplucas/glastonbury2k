package br.com.zup.payment.event;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentEvent {

    private PaymentStatus paymentStatus;
    private String orderId;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentEvent(String orderId, PaymentStatus paymentStatus, BigDecimal amount) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
