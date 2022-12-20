package br.com.zup.order.orchestrator.listener;

import java.io.IOException;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.integration.request.BookRequest;

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
		BookRequest event = this.objectMapper.readValue(message, BookRequest.class);
		String orderId = event.getOrderId();
		runtimeService.createMessageCorrelation("payment_callback").processInstanceBusinessKey("ORDER-" + orderId)
				.setVariable("PAYMENT_RESULT", true).correlateWithResult();
	}
}
