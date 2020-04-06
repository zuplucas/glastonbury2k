package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.order.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.StatusOrderApi;
import br.com.zup.order.orchestrator.integration.order.request.StatusOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentErrorTask implements JavaDelegate {


    private StatusOrderApi statusOrderApi;
    private ObjectMapper objectMapper;

    @Autowired
    public PaymentErrorTask(StatusOrderApi statusOrderApi, ObjectMapper objectMapper) {
        this.statusOrderApi = statusOrderApi;
        this.objectMapper = objectMapper;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String) delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        StatusOrderRequest statusOrderRequest = new StatusOrderRequest();
        statusOrderRequest.setId(event.getOrderId());
        statusOrderRequest.setStatus("payment error!");
        this.statusOrderApi.atualizar(statusOrderRequest);
    }

}
