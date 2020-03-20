package br.com.zup.order.orchestrator.integration.inventory.request;

import java.util.Map;

public class UnbookRequest {
    private Map<String, Integer> orderEntries;

    public UnbookRequest(Map<String, Integer> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Map<String, Integer> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Map<String, Integer> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
