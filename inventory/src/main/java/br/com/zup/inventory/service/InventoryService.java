package br.com.zup.inventory.service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.exception.NotFoundTicketException;
import br.com.zup.inventory.exception.TicketNotActiveException;
import br.com.zup.inventory.exception.TicketNotEnoughtException;

public interface InventoryService {

  void bookTicket(BookRequest request)
      throws TicketNotEnoughtException, NotFoundTicketException, TicketNotActiveException,
          Exception;

  void unbookTicket(BookRequest request)
      throws TicketNotEnoughtException, NotFoundTicketException, TicketNotActiveException,
          Exception;
}
