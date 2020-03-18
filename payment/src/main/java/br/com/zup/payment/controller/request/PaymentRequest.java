package br.com.zup.payment.controller.request;

import java.util.Map;

public class PaymentRequest {
    private Map<String, String> orderEntries;
    private String orderId;

    public Map<String, String> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Map<String, String> orderEntries) {
        this.orderEntries = orderEntries;
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}

