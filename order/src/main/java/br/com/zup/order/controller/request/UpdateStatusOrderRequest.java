package br.com.zup.order.controller.request;

public class UpdateStatusOrderRequest {

    private String orderId;

    private String Status;

    public UpdateStatusOrderRequest() {
    }

    public UpdateStatusOrderRequest(String orderId, String status) {
        this.orderId = orderId;
        Status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
