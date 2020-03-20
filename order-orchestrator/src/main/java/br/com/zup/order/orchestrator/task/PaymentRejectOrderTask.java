package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.FinishOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.RejectPaymentOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentRejectOrderTask implements JavaDelegate {

    private OrderApi orderApi;
    private ObjectMapper objectMapper;

    @Autowired
    public PaymentRejectOrderTask(OrderApi orderApi, ObjectMapper objectMapper) {
        this.orderApi = orderApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        System.out.println(this.getClass().getName() + "Order: " + orderVariable);
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        RejectPaymentOrderRequest rejectPaymentOrderRequest = new RejectPaymentOrderRequest(event.getOrderId());
        this.orderApi.rejectPaymentOrder(rejectPaymentOrderRequest);
    }
}
