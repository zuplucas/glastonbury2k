package br.com.zup.order.orchestrator.integration.order.request;

public class UpdateStatusOrderRequest {

    private String orderId;

    private String status;

    public UpdateStatusOrderRequest() {
    }

    public UpdateStatusOrderRequest(String orderId, String status) {
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
        status = status;
    }
}
