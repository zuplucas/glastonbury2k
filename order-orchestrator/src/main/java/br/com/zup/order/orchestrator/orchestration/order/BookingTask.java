package br.com.zup.order.orchestrator.orchestration.order;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.exception.InventaryErrorException;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingTask extends OrderStep {

  private final InventoryApi inventoryApi;

  @Override
  public void execute() {
    OrderCreatedEvent event = super.order.get();
    try {
      this.inventoryApi.book(new BookRequest(event.getItems()));
    } catch (FeignException ex) {
      if (ex.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
        throw new InventaryErrorException();
      }
    }
  }
}
