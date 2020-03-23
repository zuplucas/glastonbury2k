package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.UpdateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.event.OrderItemCreatedEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        String orderId = this.orderRepository.save(request.toEntity()).getId();

        Optional<Order> orderOptional = this.orderRepository.findById(orderId);
        orderOptional.ifPresent(order -> {
            OrderCreatedEvent event = new OrderCreatedEvent(
                    order.getId(),
                    order.getCustomerId(),
                    order.getAmount(),
                    order.getItems()
                            .stream()
                            .map(item -> new OrderItemCreatedEvent(item.getId(), item.getQuantity(), 100))
                            .collect(Collectors.toList())
            );

            System.out.println("ORDER: " + orderId + " => PENDING");

            this.template.send("created-orders", event);
        });

        return orderId;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void update(UpdateOrderRequest request) {
        Optional<Order> orderOptional = this.orderRepository.findById(request.getOrderId());

        orderOptional.ifPresent(order -> {
            System.out.println("ORDER: " + order.getId() + " => " + request.getStatus().toUpperCase());
            order.setStatus(request.getStatus());
            this.orderRepository.save(order);
        });
    }
}
