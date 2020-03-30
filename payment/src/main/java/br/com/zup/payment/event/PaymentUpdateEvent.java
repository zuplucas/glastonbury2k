package br.com.zup.payment.event;

public class PaymentUpdateEvent {

    private String orderId;
    private String status;


    public PaymentUpdateEvent() {
    }

    public PaymentUpdateEvent(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
