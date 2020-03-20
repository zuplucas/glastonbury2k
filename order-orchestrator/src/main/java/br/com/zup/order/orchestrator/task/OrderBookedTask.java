package br.com.zup.order.orchestrator.task;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.BookedOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.RejectOrderReason;
import br.com.zup.order.orchestrator.integration.order.request.RejectOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBookedTask implements JavaDelegate {

    private OrderApi orderApi;
    private ObjectMapper objectMapper;
@Autowired
    public OrderBookedTask(OrderApi orderApi, ObjectMapper objectMapper) {
        this.orderApi = orderApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderVariable = (String)delegateExecution.getVariable("ORDER");
        System.out.println(this.getClass().getName() + "Order: " + orderVariable);
        OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);
        BookedOrderRequest bookedOrderRequest = new BookedOrderRequest(event.getOrderId());
        this.orderApi.bookedOrder(bookedOrderRequest);
    }
}
