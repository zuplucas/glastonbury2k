package br.com.zup.order.orchestrator.listener;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.integration.camunda.CamundaApi;
import br.com.zup.order.orchestrator.integration.camunda.request.CorrelationMessageRequest;
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
public class PaymentCallbackListener {

    private final ObjectMapper objectMapper;
    private final CamundaApi camundaApi;

    public PaymentCallbackListener(ObjectMapper objectMapper, CamundaApi camundaApi) {
        this.objectMapper = objectMapper;
        this.camundaApi = camundaApi;
    }

    @KafkaListener(topics = "payment-event", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {
        // Read message, parse, check orderId and payment result
        String orderId = "TO BE DEFINED";

        System.out.println("Sending payment response" + orderId);

        Map<String, VariableItem> variables = new HashMap<>();
        variables.put("PAYMENT_RESULT", new VariableItem("true", ValueType.BOOLEAN.getName()));
        CorrelationMessageRequest correlation = CorrelationMessageRequest.fromBusinessKey(
                "payment_callback", "ORDER-" + orderId, variables);
        try {
            System.out.println(objectMapper.writeValueAsString(correlation));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        camundaApi.sendCorrelationMessage(correlation);
        System.out.println("Finished sending payment " + orderId);
    }
}
