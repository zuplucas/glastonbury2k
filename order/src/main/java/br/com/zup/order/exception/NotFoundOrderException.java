package br.com.zup.order.exception;

public class NotFoundOrderException extends BussinesException {

  public NotFoundOrderException(String ticketId) {
    super("8080-1", String.format("Not Found Order id '%s'", ticketId));
  }
}
