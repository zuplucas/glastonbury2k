package br.com.zup.order.orchestrator.integration.inventory.request;

public class UndoBookRequest {

    private String orderId;

    public UndoBookRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
