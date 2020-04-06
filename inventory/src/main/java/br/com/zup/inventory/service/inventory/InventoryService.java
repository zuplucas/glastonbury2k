package br.com.zup.inventory.service.inventory;

import br.com.zup.inventory.controller.inventory.request.BookRequest;

public interface InventoryService {

    Boolean save(BookRequest request);

}
