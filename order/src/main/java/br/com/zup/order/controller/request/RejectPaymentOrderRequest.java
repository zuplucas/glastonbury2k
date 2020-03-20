package br.com.zup.order.controller.request;

public class RejectPaymentOrderRequest {
    private String orderId;

    public RejectPaymentOrderRequest(String orderId) {
        this.orderId = orderId;
    }

    public RejectPaymentOrderRequest() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
