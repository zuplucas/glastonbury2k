package br.com.zup.order.orchestrator.orchestration.order;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnbookingTask extends OrderStep {

  private final InventoryApi inventoryApi;

  @Override
  public void execute() {
    OrderCreatedEvent event = super.order.get();
    this.inventoryApi.unbook(new BookRequest(event.getItems()));
  }
}
