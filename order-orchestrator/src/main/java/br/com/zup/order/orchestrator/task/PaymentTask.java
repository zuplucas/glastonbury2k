package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.RejectOrderReason;
import br.com.zup.order.orchestrator.integration.order.request.RejectOrderRequest;
import br.com.zup.order.orchestrator.integration.payment.PaymentApi;
import br.com.zup.order.orchestrator.integration.payment.request.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        System.out.println(this.getClass().getName() + "Order: " + orderVariable);
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        PaymentRequest paymentRequest = new PaymentRequest(event.getOrderId(), event.getAmount());
        this.paymentApi.pay(paymentRequest);
    }
}
