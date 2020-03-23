package br.com.zup.inventory.controller.request;

import br.com.zup.inventory.entity.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookRequest {

    private String id;
    private String orderId;
    private List<BookItemRequest> entries;

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

    public List<BookItemRequest> getEntries() {
        return entries;
    }

    public void setEntries(List<BookItemRequest> entries) {
        this.entries = entries;
    }

    public Book toEntity() {
        return new Book(
                UUID.randomUUID().toString(),
                this.orderId,
                this.entries.stream()
                        .map(BookItemRequest::toEntity)
                        .collect(Collectors.toList()),
                "booked"
        );
    }


}
