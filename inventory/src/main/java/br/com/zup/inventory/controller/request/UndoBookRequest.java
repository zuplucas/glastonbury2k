package br.com.zup.inventory.controller.request;

public class UndoBookRequest {

    private String orderId;

    public UndoBookRequest() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
