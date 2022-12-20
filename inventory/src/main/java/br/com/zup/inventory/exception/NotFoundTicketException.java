package br.com.zup.inventory.exception;

public class NotFoundTicketException extends BussinesException {

  public NotFoundTicketException(String ticketId) {
    super("8181-1", String.format("Not Found Ticket id '%s'", ticketId));
  }
}
