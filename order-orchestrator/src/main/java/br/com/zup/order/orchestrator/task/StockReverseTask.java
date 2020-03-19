package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.ReverseStockRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class StockReverseTask implements JavaDelegate{

    private InventoryApi inventoryApi;
    private ObjectMapper objectMapper;

    public StockReverseTask(InventoryApi inventoryApi, ObjectMapper objectMapper) {
        this.inventoryApi = inventoryApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        OrderCreatedEvent order = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        ReverseStockRequest reverse = new ReverseStockRequest(order);
        this.inventoryApi.reverseStock(reverse);
    }
}
