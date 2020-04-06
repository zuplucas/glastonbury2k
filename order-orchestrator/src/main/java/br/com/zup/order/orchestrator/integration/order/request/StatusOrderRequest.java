package br.com.zup.order.orchestrator.integration.order.request;

public class StatusOrderRequest {

    private String id;
    private String status;

    public StatusOrderRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
