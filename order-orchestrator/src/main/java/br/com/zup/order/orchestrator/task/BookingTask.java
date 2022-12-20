package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class BookingTask implements JavaDelegate{

    private InventoryApi inventoryApi;
    private ObjectMapper objectMapper;

    public BookingTask(InventoryApi inventoryApi, ObjectMapper objectMapper) {
        this.inventoryApi = inventoryApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        BookRequest bookRequest = new BookRequest();
        bookRequest.setOrderEntries(event.getItems());
        this.inventoryApi.book(bookRequest);
    }
}
