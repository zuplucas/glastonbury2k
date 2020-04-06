package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.order.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.payment.PaymentApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentTask implements JavaDelegate{

    private KafkaTemplate<String, OrderCreatedEvent> template;
    private PaymentApi paymentApi;

    private ObjectMapper objectMapper;

    @Autowired
    public PaymentTask(ObjectMapper objectMapper, KafkaTemplate<String, OrderCreatedEvent> template, PaymentApi paymentApi) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.paymentApi = paymentApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);

        event.setStatus(this.paymentApi.payment());
        this.template.send("payment-event", event);

    }
}
