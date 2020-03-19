package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.UpdateStatusOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.StatusItem;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.event.OrderCreatedItemEvent;
import br.com.zup.order.event.OrderCreatedStatusEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        Order order = this.orderRepository.save(request.toEntity());

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                order.getItems().stream().map(item -> new OrderCreatedItemEvent(item.getId(), item.getQuantity())).collect(Collectors.toList()),
                order.getStatus().stream().map(status -> new OrderCreatedStatusEvent(status.getId(), status.getDate(), status.getStatus())).collect(Collectors.toList())
        );

        this.template.send("created-orders", event);

        return order.getId();
    }

    private OrderCreatedItemEvent getItems(CreateOrderRequest.OrderItemPart item) {
        return new OrderCreatedItemEvent(item.getId(), item.getQuantity());
    }

    private Map<String, Integer> createItemMap(CreateOrderRequest request) {
        Map<String, Integer> result = new HashMap<>();
        for (CreateOrderRequest.OrderItemPart item : request.getItems()) {
            result.put(item.getId(), item.getQuantity());
        }

        return result;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String update(UpdateStatusOrderRequest request) {
        Optional<Order> order = this.orderRepository.findById(request.getOrderId());
        if (!order.isPresent()) {
            return "";
        }
        order.get().getStatus().add(new StatusItem(request.getStatus()));
        return this.orderRepository.save(order.get()).getId();
    }
}
