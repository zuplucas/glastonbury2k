package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.integration.orchestrator.OrderOrchestratorApi;
import br.com.zup.order.integration.orchestrator.request.StartFlowRequest;
import br.com.zup.order.integration.orchestrator.response.StartFlowResponse;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderOrchestratorApi orchestratorApi;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderOrchestratorApi orchestratorApi) {
        this.orderRepository = orderRepository;
        this.orchestratorApi = orchestratorApi;
    }

    @Override
    public String save(CreateOrderRequest request) {
        String orderId = this.orderRepository.save(request.toEntity()).getId();

        StartFlowRequest flowRequest = new StartFlowRequest(
                orderId,
                request.getCustomerId(),
                request.getAmount(),
                createItemMap(request)
        );

        StartFlowResponse orchestratorResponse = orchestratorApi.startFlow(flowRequest);
        System.out.println("Orchestrator process " + orchestratorResponse.processId() +
                " for order " + orchestratorResponse.orderId());
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
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
