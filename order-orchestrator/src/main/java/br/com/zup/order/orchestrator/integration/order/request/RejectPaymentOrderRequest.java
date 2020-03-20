package br.com.zup.order.orchestrator.integration.order.request;

public class RejectPaymentOrderRequest {
    private String orderId;

    public RejectPaymentOrderRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
