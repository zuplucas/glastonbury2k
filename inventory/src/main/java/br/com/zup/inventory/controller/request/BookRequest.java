package br.com.zup.inventory.controller.request;

import java.util.List;

public class BookRequest {

    private String orderId;
    private List<BookRequestItem> orderEntries;

    public List<BookRequestItem> getOrderEntries() {
        return orderEntries;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderEntries(List<BookRequestItem> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
