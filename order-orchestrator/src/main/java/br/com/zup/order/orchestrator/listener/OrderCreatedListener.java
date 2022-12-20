package br.com.zup.order.orchestrator.listener;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderCreatedListener {

    private ObjectMapper objectMapper;
    private RuntimeService runtimeService;

    public OrderCreatedListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
        this.objectMapper = objectMapper;
        this.runtimeService = runtimeService;
    }

    @KafkaListener(topics = "created-orders", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        System.out.println(event);

        runtimeService.startProcessInstanceByKey("order-process",
                "ORDER-" + event.getOrderId(),
                Variables.putValue("ORDER", message));
    }
}
