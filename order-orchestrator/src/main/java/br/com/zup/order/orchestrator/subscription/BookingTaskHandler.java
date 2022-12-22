package br.com.zup.order.orchestrator.subscription;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingTaskHandler {

    private final InventoryApi inventoryApi;
    private final ObjectMapper objectMapper;

    public BookingTaskHandler(InventoryApi inventoryApi, ObjectMapper objectMapper) {
        this.inventoryApi = inventoryApi;
        this.objectMapper = objectMapper;
    }

    public void handleBook(String orderJson) {
        OrderCreatedEvent event = null;
        try {
            event = this.objectMapper.readValue(orderJson, OrderCreatedEvent.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            throw new RuntimeException("Failure to parse ORDER variable");
        }
        BookRequest bookRequest = new BookRequest();
        bookRequest.setOrderEntries(event.getItems());
        this.inventoryApi.book(bookRequest);
    }
}
