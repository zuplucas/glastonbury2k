package br.com.zup.inventory.controller.inventory.request;

public class BookItemRequest {

    private String idItem;
    private Integer quantity;

    public BookItemRequest() {
    }

    public BookItemRequest(String idItem, Integer quantity) {
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

