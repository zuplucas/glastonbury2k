package br.com.zup.inventory.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "books")
public class Book {

    @Id
    private String id;

    private String orderId;

    @ManyToMany
    @Cascade(CascadeType.ALL)
    private List<BookItem> items;

    private String status;

    public Book() {
    }

    public Book(String id, String orderId, List<BookItem> items, String status) {
        this.id = id;
        this.orderId = orderId;
        this.items = items;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<BookItem> getItems() {
        return items;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

