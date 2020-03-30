package br.com.zup.order.orchestrator.task;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.event.OrderItemPartEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequestItem;

@Component
public class BookingTask implements JavaDelegate {

    private InventoryApi inventoryApi;
    private ObjectMapper objectMapper;

    @Autowired
    public BookingTask(InventoryApi inventoryApi, ObjectMapper objectMapper) {
        this.inventoryApi = inventoryApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String) delegateExecution.getVariable("ORDER");
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        BookRequest bookRequest = new BookRequest();
        bookRequest.setOrderId(event.getOrderId());
        bookRequest.setOrderEntries(createItems(event));
        try {
            this.inventoryApi.book(bookRequest);
        } catch (RuntimeException ex) {
            System.out.println("Throwing RuntimeException");
            throw ex;
        }
    }

    private List<BookRequestItem> createItems(OrderCreatedEvent event) {
        List<BookRequestItem> items = new ArrayList<>();

        for (OrderItemPartEvent item : event.getOrderItems()) {
            BookRequestItem eventItem = new BookRequestItem(item.getId(), item.getName(), item.getAmount(),
                    item.getQuantity());
            items.add(eventItem);
        }

        return items;
    }

}
