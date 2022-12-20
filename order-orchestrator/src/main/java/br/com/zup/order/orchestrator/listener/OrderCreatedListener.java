package br.com.zup.order.orchestrator.listener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;

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

        Map<String, Object> variables = new HashMap<>();
        variables.put("ORDER", message);
        variables.put("ORDER-ID", event.getOrderId());

        runtimeService.startProcessInstanceByKey("order-process",
                "ORDER-" + event.getOrderId(), variables);

    }
}
