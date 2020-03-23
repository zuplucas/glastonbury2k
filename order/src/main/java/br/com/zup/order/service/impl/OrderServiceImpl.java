package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.OrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.exception.NotFoundOrderException;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final KafkaTemplate<String, OrderCreatedEvent> template;

  @Override
  public String save(CreateOrderRequest request) {
    String orderId = this.orderRepository.save(request.toEntity()).getId();

    OrderCreatedEvent event =
        new OrderCreatedEvent(
            orderId, request.getCustomerId(), request.getAmount(), createItemMap(request));

    this.template.send("created-orders", event);

    return orderId;
  }

  public void save(OrderRequest request) throws NotFoundOrderException {
    Order order =
        this.orderRepository
            .findById(request.getOrderId())
            .orElseThrow(() -> new NotFoundOrderException(request.getOrderId()));
    order.setStatus(request.getOrderStatus().toString());
    this.orderRepository.save(order);
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
    return this.orderRepository.findAll().stream()
        .map(OrderResponse::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public OrderResponse findById(String id) {
    return this.orderRepository.findById(id).map(OrderResponse::fromEntity).get();
  }
}
