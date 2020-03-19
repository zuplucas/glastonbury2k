package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.payment.PaymentApi;
import br.com.zup.order.orchestrator.integration.payment.request.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PaymentTask implements JavaDelegate{

    private ObjectMapper objectMapper;
    private PaymentApi paymentApi;

    public PaymentTask(PaymentApi paymentApi, ObjectMapper objectMapper) {
        this.paymentApi = paymentApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        OrderCreatedEvent order = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        PaymentRequest payment = new PaymentRequest(order);
        this.paymentApi.pay(payment);
    }
}
