package br.com.zup.order.orchestrator.event;

public class PaymentEvent {

    private String orderId;
    private String status;

    public PaymentEvent(String status, String orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
