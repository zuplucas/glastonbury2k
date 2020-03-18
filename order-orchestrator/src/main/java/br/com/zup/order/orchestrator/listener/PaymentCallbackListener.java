package br.com.zup.order.orchestrator.listener;

import java.io.IOException;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;

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
        // Read message, parse, check orderId and payment result
    	Map<String, String> event = this.objectMapper.readValue(message, Map.class);
        String orderId = event.get("orderId");

        int low = 10;
        int high = 100;
        int r = (int) (Math.random() * (high - low)) + low;
        boolean result = (r % 2) == 0;

        runtimeService.createMessageCorrelation("payment_callback")
                .processInstanceBusinessKey("ORDER-" + orderId)
                .setVariable("PAYMENT_RESULT", result)
                .setVariable("ORDER_LIST", message)
                .correlateWithResult();
    }
}
