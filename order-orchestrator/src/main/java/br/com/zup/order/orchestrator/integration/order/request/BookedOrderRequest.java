package br.com.zup.order.orchestrator.integration.order.request;

public class BookedOrderRequest {
    private String orderId;

    public BookedOrderRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
