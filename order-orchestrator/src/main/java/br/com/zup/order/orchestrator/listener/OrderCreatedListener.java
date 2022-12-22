package br.com.zup.order.orchestrator.listener;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.camunda.CamundaApi;
import br.com.zup.order.orchestrator.integration.camunda.request.StartProcessRequest;
import br.com.zup.order.orchestrator.integration.camunda.request.VariableItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderCreatedListener {

    private final ObjectMapper objectMapper;

    private final CamundaApi camundaApi;

    public OrderCreatedListener(ObjectMapper objectMapper, CamundaApi camundaApi) {
        this.objectMapper = objectMapper;
        this.camundaApi = camundaApi;
    }

    @KafkaListener(topics = "created-orders", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        System.out.println(event);

        startWorkflowProcess(event, message);
    }

    private void startWorkflowProcess(OrderCreatedEvent event, String message) throws JsonProcessingException {
        Map<String, VariableItem> variables = new HashMap<>();

        variables.put("ORDER", new VariableItem(message, ValueType.STRING.getName()));

        StartProcessRequest request = new StartProcessRequest(variables,
                "ORDER-" + event.getOrderId());

        System.out.println(objectMapper.writeValueAsString(request));
        camundaApi.startProcess("order-process", request);
    }
}
