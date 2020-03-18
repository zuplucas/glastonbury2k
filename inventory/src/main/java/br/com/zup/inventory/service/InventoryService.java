package br.com.zup.inventory.service;

import br.com.zup.inventory.controller.request.BookRequest;

public interface InventoryService {

    void orderPurchase(BookRequest request);

}
