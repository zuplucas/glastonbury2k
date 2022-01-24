package br.com.zup.order.orchestrator.event.order;

public class OrderItemCreatedEvent {

    private String idItem;
    private Integer quantity;

    public OrderItemCreatedEvent() {
    }

    public OrderItemCreatedEvent(String idItem, Integer quantity) {
        this.idItem = idItem;
        this.quantity = quantity;
    }

    public String getItem() {
        return idItem;
    }

    public void setItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

