package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.response.ErrorResponse;
import br.com.zup.inventory.exception.BussinesException;
import br.com.zup.inventory.exception.NotFoundTicketException;
import br.com.zup.inventory.exception.TicketNotActiveException;
import br.com.zup.inventory.exception.TicketNotEnoughtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HandleException {

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler({
    NotFoundTicketException.class,
    TicketNotActiveException.class,
    TicketNotEnoughtException.class
  })
  public ErrorResponse handleException(BussinesException exception) {
    return new ErrorResponse(exception.getCode(), exception.getMessage());
  }
}
