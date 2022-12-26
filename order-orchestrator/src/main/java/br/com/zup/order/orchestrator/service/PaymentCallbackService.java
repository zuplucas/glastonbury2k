package br.com.zup.order.orchestrator.service;

import br.com.zup.order.orchestrator.integration.camunda.CamundaApi;
import br.com.zup.order.orchestrator.integration.camunda.request.CorrelationMessageRequest;
import br.com.zup.order.orchestrator.integration.camunda.request.VariableItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentCallbackService {
    private final ObjectMapper objectMapper;
    private final CamundaApi camundaApi;

    public PaymentCallbackService(ObjectMapper objectMapper, CamundaApi camundaApi) {
        this.objectMapper = objectMapper;
        this.camundaApi = camundaApi;
    }

    public void process(String orderId, Object paymentResponse) {
        System.out.println("Sending payment response" + orderId);

        boolean isPaymentOk = true;

        Map<String, VariableItem> variables = new HashMap<>();
        variables.put("PAYMENT_RESULT", new VariableItem(Boolean.toString(isPaymentOk), ValueType.BOOLEAN.getName()));

        CorrelationMessageRequest correlation = CorrelationMessageRequest.fromBusinessKey(
                "payment_callback", "ORDER-" + orderId, variables);

        try {
            System.out.println(objectMapper.writeValueAsString(correlation));

            if (paymentResponse != null) {
                System.out.println("Payment response " + objectMapper.writeValueAsString(paymentResponse));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        camundaApi.sendCorrelationMessage(correlation);
        System.out.println("Finished sending payment " + orderId);
    }
}
