package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.UnbookRequest;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnbookTicketsTask implements JavaDelegate {

    private InventoryApi inventoryApi;
    private ObjectMapper objectMapper;
    @Autowired
    public UnbookTicketsTask(InventoryApi inventoryApi, ObjectMapper objectMapper) {
        this.inventoryApi = inventoryApi;
        this.objectMapper = objectMapper;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        System.out.println(this.getClass().getName() + "Order: " + orderVariable);
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        UnbookRequest unbookRequest = new UnbookRequest(event.getItems());
        this.inventoryApi.unbook(unbookRequest);
    }
}
