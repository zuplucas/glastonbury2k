package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.OrderApi;
import br.com.zup.order.orchestrator.integration.request.BookRequest;

@Component
public class OrderTask implements JavaDelegate {

	private static final String TASK_CANCEL_ORDER = "TASK_CANCEL_ORDER";
	private static final String TASK_FINISH_ORDER = "TASK_FINISH_ORDER";
	private static final String ORDER = "ORDER";
	private OrderApi orderApi;
	private ObjectMapper objectMapper;

	public OrderTask(OrderApi orderApi, ObjectMapper objectMapper) {
		this.orderApi = orderApi;
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
		case TASK_FINISH_ORDER:
			this.orderApi.finishOrder(bookRequest);
			break;
		case TASK_CANCEL_ORDER:
			this.orderApi.cancelOrder(bookRequest);
			break;
		default:
			break;
		}
	}
}
