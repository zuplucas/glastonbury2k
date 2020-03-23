package br.com.zup.order.event;

public class OrderItemCreatedEvent {

    private String id;
    private Integer quantity;
    private Integer maxQuantity;

    public OrderItemCreatedEvent(){
    }

    public OrderItemCreatedEvent(String id, Integer quantity, Integer maxQuantity) {
        this.id = id;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
