package br.com.zup.order.orchestrator.listener;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.enums.PaymentStatus;
import br.com.zup.order.orchestrator.event.PaymentCallbackEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentCallbackListener {

  private ObjectMapper objectMapper;
  private RuntimeService runtimeService;

  @KafkaListener(topics = "payment-callback", groupId = KafkaConfiguration.CONSUMER_GROUP)
  public void listen(String message) throws IOException {

    PaymentCallbackEvent event = this.objectMapper.readValue(message, PaymentCallbackEvent.class);
    System.out.println(event);
    String orderId = event.getOrderId();

    runtimeService
        .createMessageCorrelation("payment_callback")
        .processInstanceBusinessKey("order-" + orderId)
        .setVariable("PAYMENT_RESULT", PaymentStatus.APPROVED == event.getPaymentStatus())
        .correlateWithResult();
  }
}
