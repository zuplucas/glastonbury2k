package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.entity.Ticket;
import br.com.zup.inventory.exception.NotFoundTicketException;
import br.com.zup.inventory.exception.TicketNotActiveException;
import br.com.zup.inventory.exception.TicketNotEnoughtException;
import br.com.zup.inventory.repository.TicketRepository;
import br.com.zup.inventory.service.InventoryService;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

  private TicketRepository ticketRepository;

  @Autowired
  public InventoryServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
    this.ticketRepository.save(new Ticket("3852cb18-9c19-4326-ac77-a1a0264bd98c", 100, true));
  }

  @Override
  @Transactional
  public void bookTicket(BookRequest request) throws Exception {
    processTicket(request, true);
  }

  @Override
  public void unbookTicket(BookRequest request) throws Exception {
    processTicket(request, false);
  }

  private void processTicket(BookRequest request, boolean addTicket) throws Exception {
    Iterator<Map.Entry<String, Integer>> iterator = request.iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, Integer> ticketRequest = iterator.next();
      Ticket ticket =
          this.ticketRepository
              .findById(ticketRequest.getKey())
              .orElseThrow(() -> new NotFoundTicketException(ticketRequest.getKey()));
      if (!ticket.getActive()) {
        throw new TicketNotActiveException(ticketRequest.getKey());
      } else if (addTicket && ticket.getQuantity() < ticketRequest.getValue()) {
        throw new TicketNotEnoughtException(
            ticketRequest.getKey(), ticket.getQuantity(), ticketRequest.getValue());
      } else {
        if (addTicket) {
          ticket.setQuantity(ticket.getQuantity() - ticketRequest.getValue());
        } else {
          ticket.setQuantity(ticket.getQuantity() + ticketRequest.getValue());
        }
        this.ticketRepository.save(ticket);
      }
    }
  }
}
