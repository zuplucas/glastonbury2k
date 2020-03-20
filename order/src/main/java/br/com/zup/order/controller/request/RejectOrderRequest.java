package br.com.zup.order.controller.request;

public class RejectOrderRequest {
    private String orderId;
    private RejectOrderReason rejectReason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RejectOrderReason getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(RejectOrderReason rejectReason) {
        this.rejectReason = rejectReason;
    }
}
