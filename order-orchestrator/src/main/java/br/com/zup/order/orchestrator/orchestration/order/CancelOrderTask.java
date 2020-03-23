package br.com.zup.order.orchestrator.orchestration.order;

import br.com.zup.order.orchestrator.enums.OrderStatus;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelOrderTask extends OrderStep {

  private final OrderApi orderApi;

  @Override
  public void execute() {
    OrderCreatedEvent event = super.order.get();
    this.orderApi.updateOrder(new OrderRequest(event.getOrderId(), OrderStatus.CANCELED));
  }
}
