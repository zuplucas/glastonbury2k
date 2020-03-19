package br.com.zup.order.orchestrator.listener;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentCallbackListener {

    private ObjectMapper objectMapper;
    private RuntimeService runtimeService;

    public PaymentCallbackListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
        this.objectMapper = objectMapper;
        this.runtimeService = runtimeService;
    }

    @KafkaListener(topics = "payment-event", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {
        PaymentEvent event = this.objectMapper.readValue(message, PaymentEvent.class);
        String orderId = event.getOrder().getOrderId();

        runtimeService.createMessageCorrelation("payment_callback")
                .processInstanceBusinessKey("ORDER-" + orderId)
                .setVariable("PAYMENT_RESULT", event.isPaymentApproved())
                .correlateWithResult();
    }
}
