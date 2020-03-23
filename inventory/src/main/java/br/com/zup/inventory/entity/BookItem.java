package br.com.zup.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "book_items")
public class BookItem {

    @Id
    private String id;

    private Integer quantity;

    private Integer maxQuantity;

    public BookItem() {
    }

    public BookItem(String id, Integer quantity, Integer maxQuantity) {
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
