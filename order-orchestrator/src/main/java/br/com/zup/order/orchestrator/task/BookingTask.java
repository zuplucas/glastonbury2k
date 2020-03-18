package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;

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

        if(event.getItems() == null || event.getItems().size() == 0 || !event.getCustomerId().contains("23655ac04b8a")) {
//        	throw new BookException();
        	throw new BpmnError("428");
        }

        bookRequest.setOrderEntries(event.getItems());
        bookRequest.getOrderEntries().put("orderId", event.getOrderId());
        this.inventoryApi.book(bookRequest);
    }
}
