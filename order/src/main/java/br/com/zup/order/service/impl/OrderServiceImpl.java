package br.com.zup.order.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.event.OrderItemPartEvent;
import br.com.zup.order.event.UpdateOrderEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import javassist.NotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, OrderCreatedEvent> template;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, OrderCreatedEvent> template) {
        this.orderRepository = orderRepository;
        this.template = template;
    }

    @Override
    public String save(CreateOrderRequest request) {
        String orderId = this.orderRepository.save(request.toEntity()).getId();

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getCustomerId(),
                request.getAmount(),
                createItems(request)
        );

        this.template.send("created-orders", event);

        return orderId;
    }

    @Override
    public List<OrderResponse> find() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(String orderId) throws NotFoundException {
        updateOrderStatus(orderId, "CANCELLED");
    }

    @Override
    public void completeOrder(String orderId) throws NotFoundException {
        updateOrderStatus(orderId, "COMPLETED");
    }

    @Override
    public void rejectOrder(String orderId) throws NotFoundException {
        updateOrderStatus(orderId, "PAYMENT_REJECTED");
    }

    public void updateOrder(UpdateOrderEvent event) throws NotFoundException {
        updateOrderStatus(event.getOrderId(), event.getStatus());
    }

    private List<OrderItemPartEvent> createItems(CreateOrderRequest request) {
        List<OrderItemPartEvent> items = new ArrayList<>();

        for (CreateOrderRequest.OrderItemPart item : request.getItems()) {
            OrderItemPartEvent eventItem = new OrderItemPartEvent(item.getId(), item.getName(), item.getAmount(),
                    item.getQuantity());
            items.add(eventItem);
        }

        return items;
    }

    private void updateOrderStatus(String orderId, String status) throws NotFoundException {
        Optional<Order> optOrder = this.orderRepository.findById(orderId);
        Order order = optOrder.orElse(null);

        if (null != order) {
            order.setStatus(status);
            this.orderRepository.save(order);
        } else {
            throw new NotFoundException("Order");
        }
    }

}
