package br.com.zup.inventory.exception;

public class TicketNotActiveException extends BussinesException {

  public TicketNotActiveException(String ticketId) {
    super("8081-2", String.format("Ticket id '%s' not active", ticketId));
  }
}
