package br.com.zup.inventory.exception;

public class TicketNotEnoughtException extends BussinesException {

  public TicketNotEnoughtException(String ticketId, int availableQuantity, int requestedQuantity) {
    super(
        "8181-3",
        String.format(
            "Ticket id '%s' not enought, available quantity '%d', available quantity '%d' !",
            ticketId, availableQuantity, requestedQuantity));
  }
}
