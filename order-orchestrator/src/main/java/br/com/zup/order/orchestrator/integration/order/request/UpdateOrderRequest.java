package br.com.zup.order.orchestrator.integration.order.request;

public class UpdateOrderRequest {
    private String orderId;
    private String status;

    public UpdateOrderRequest() {
    }

    public UpdateOrderRequest(String orderId, String status) {
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
