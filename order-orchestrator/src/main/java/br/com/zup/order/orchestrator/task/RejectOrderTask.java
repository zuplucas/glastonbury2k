package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.UpdateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectOrderTask implements JavaDelegate {

    private ObjectMapper objectMapper;
    private OrderApi orderApi;

    @Autowired
    public RejectOrderTask(ObjectMapper objectMapper, OrderApi orderApi) {
        this.objectMapper = objectMapper;
        this.orderApi = orderApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String) delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        this.orderApi.update(new UpdateOrderRequest(event.getOrderId(), "REJECT"));
    }
}
