package br.com.zup.inventory.controller.request;

import java.util.Map;

public class BookRequest {
    private Map<String, Integer> orderEntries;

    public Map<String, Integer> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Map<String, Integer> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
