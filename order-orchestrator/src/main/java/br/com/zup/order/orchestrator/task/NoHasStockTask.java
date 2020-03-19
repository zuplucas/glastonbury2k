package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.UpdateStatusOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class NoHasStockTask implements JavaDelegate{

    private OrderApi orderApi;
    private ObjectMapper objectMapper;
    private String NO_HAS_STOCK = "Order Items Not Stock";

    public NoHasStockTask(OrderApi orderApi, ObjectMapper objectMapper) {
        this.orderApi = orderApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        OrderCreatedEvent order = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        UpdateStatusOrderRequest status = new UpdateStatusOrderRequest(order.getOrderId(), NO_HAS_STOCK);
        this.orderApi.updateStatus(status);
    }
}
