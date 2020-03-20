package br.com.zup.order.orchestrator.integration.order.request;

import java.math.BigDecimal;

public class RejectOrderRequest {

    private String orderId;
    private RejectOrderReason rejectReason;


    public RejectOrderRequest(String orderId, RejectOrderReason rejectOrderReason) {
        this.orderId = orderId;
        this.rejectReason = rejectOrderReason;
    }

    public RejectOrderReason getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(RejectOrderReason rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
