package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.UndoBookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnbookTask implements JavaDelegate {

    private ObjectMapper objectMapper;
    private InventoryApi inventoryApi;

    @Autowired
    public UnbookTask(ObjectMapper objectMapper, InventoryApi inventoryApi) {
        this.objectMapper = objectMapper;
        this.inventoryApi = inventoryApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String) delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        this.inventoryApi.undo(new UndoBookRequest(event.getOrderId()));
    }
}
