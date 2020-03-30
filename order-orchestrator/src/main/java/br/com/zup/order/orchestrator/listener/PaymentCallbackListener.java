package br.com.zup.order.orchestrator.listener;

import java.io.IOException;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.event.PaymentEvent;

@Component
public class PaymentCallbackListener {

    private ObjectMapper objectMapper;
    private RuntimeService runtimeService;

    public PaymentCallbackListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
        this.objectMapper = objectMapper;
        this.runtimeService = runtimeService;
    }

    @KafkaListener(topics = "payment-events", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {

        PaymentEvent paymentEvent = objectMapper.readValue(message, PaymentEvent.class);
        boolean paymentResult = paymentEvent.getStatus().equals("PAYMENT_ACCEPTED");

        runtimeService.createMessageCorrelation("payment_callback")
                .processInstanceBusinessKey("ORDER-" + paymentEvent.getOrderId())
                .setVariable("PAYMENT_RESULT", paymentResult)
                .correlateWithResult();
    }
}
