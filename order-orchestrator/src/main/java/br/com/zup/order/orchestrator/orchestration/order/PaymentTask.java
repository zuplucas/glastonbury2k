package br.com.zup.order.orchestrator.orchestration.order;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.payment.PaymentApi;
import br.com.zup.order.orchestrator.integration.payment.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentTask extends OrderStep {

  private final PaymentApi paymentApi;

  @Override
  public void execute() {
    OrderCreatedEvent event = super.order.get();
    System.out.println(event);
    this.paymentApi.pay(new PaymentRequest(event.getOrderId(), event.getAmount()));
  }
}
