package br.com.zup.inventory.service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.UnbookRequest;

public interface InventoryService {
    boolean checkTicketAvailability(BookRequest bookRequest);
    void rollbackTicketAvailability(UnbookRequest unbookRequest);
}
