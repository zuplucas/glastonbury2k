package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.payment.PaymentApi;
import br.com.zup.order.orchestrator.integration.payment.request.PaymentRequest;

@Component
public class PaymentTask implements JavaDelegate {

    private PaymentApi paymentApi;
    private ObjectMapper objectMapper;

    @Autowired
    public PaymentTask(PaymentApi paymentApi, ObjectMapper objectMapper) {
        this.paymentApi = paymentApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String) delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        PaymentRequest request = new PaymentRequest(event.getAmount(), event.getOrderId());

        paymentApi.pay(request);
    }

}
