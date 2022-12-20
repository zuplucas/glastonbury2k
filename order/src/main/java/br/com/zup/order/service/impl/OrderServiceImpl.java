package br.com.zup.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.order.controller.request.BookRequest;
import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final String REJECT = "REJECT";
	private static final String SOLD_OUT = "Sold-Out";
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

		OrderCreatedEvent event = new OrderCreatedEvent(orderId, request.getCustomerId(), request.getAmount(),
				createItemMap(request));

		this.template.send("created-orders", event);

		return orderId;
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
		return this.orderRepository.findAll().stream().map(OrderResponse::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void finishOrder(BookRequest request) {
		if (request != null) {
			Order order = this.orderRepository.findById(request.getOrderId()).orElse(null);
			order.setStatus(SOLD_OUT);
			orderRepository.save(order);
		}
	}

	@Override
	public void cancelOrder(BookRequest request) {
		if (request != null) {
			Order order = this.orderRepository.findById(request.getOrderId()).orElse(null);
			order.setStatus(REJECT);
			orderRepository.save(order);
		}
	}

}
