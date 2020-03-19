package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.InventoryApi;
import br.com.zup.order.orchestrator.integration.request.BookRequest;

@Component
public class BookingTask implements JavaDelegate {

	private static final String ORDER = "ORDER";
	private static final String TASK_BOOK_TICKET = "TASK_BOOK_TICKET";

	private InventoryApi inventoryApi;
	private ObjectMapper objectMapper;

	public BookingTask(InventoryApi inventoryApi, ObjectMapper objectMapper) {
		this.inventoryApi = inventoryApi;
		this.objectMapper = objectMapper;
	}

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		String orderVariable = (String) delegateExecution.getVariable(ORDER);
		OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
		BookRequest bookRequest = new BookRequest();
		bookRequest.setOrderId(event.getOrderId());
		bookRequest.setOrderEntries(event.getItems());

		switch (delegateExecution.getCurrentActivityId()) {
		case TASK_BOOK_TICKET:
			if (!this.inventoryApi.book(bookRequest)) {
				System.out.println("TEST ERROR TEST ERROR TEST ERROR TEST ERROR ");
				throw new BpmnError("InventoryApiError", "Error Message");
			}
			break;
		default:
			break;
		}
	}
}
