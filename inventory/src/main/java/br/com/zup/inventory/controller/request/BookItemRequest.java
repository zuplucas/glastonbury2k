package br.com.zup.inventory.controller.request;

import br.com.zup.inventory.entity.BookItem;

public class BookItemRequest {
    private String id;
    private Integer quantity;
    private Integer maxQuantity;

    public BookItemRequest(String id, Integer quantity, Integer maxQuantity) {
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

    public BookItem toEntity() {
        return new BookItem(
                this.id,
                this.quantity,
                this.maxQuantity
        );
    }
}
