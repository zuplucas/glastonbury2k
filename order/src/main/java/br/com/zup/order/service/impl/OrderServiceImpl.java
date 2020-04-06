package br.com.zup.order.service.impl;

import br.com.zup.order.controller.order.request.CreateOrderRequest;
import br.com.zup.order.controller.order.response.OrderResponse;
import br.com.zup.order.controller.status.request.StatusOrderRequest;
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

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getCustomerId(),
                request.getAmount(),
                request.getItems().stream().map(itens ->
                        new OrderItemCreatedEvent(itens.getId(),
                                itens.getQuantity())).
                        collect(Collectors.toList())
        );

        this.template.send("created-orders", event);

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
    public void updateStatusOrder(StatusOrderRequest statusOrderRequest){

        Optional<Order> orderReject = orderRepository.findById(statusOrderRequest.getId());
        orderReject.get().setStatus(statusOrderRequest.getStatus());

        this.orderRepository.save(orderReject.get());

    }


}
