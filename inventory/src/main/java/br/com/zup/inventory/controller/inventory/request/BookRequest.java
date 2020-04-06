package br.com.zup.inventory.controller.inventory.request;

import java.util.List;

public class BookRequest {

    private List<BookItemRequest> orderEntries;

    public List<BookItemRequest> getOrderEntries()
    {
        return orderEntries;
    }

    public void setOrderEntries(List<BookItemRequest> items)
    {
        this.orderEntries = items;
    }
}
