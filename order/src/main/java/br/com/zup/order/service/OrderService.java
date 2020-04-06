package br.com.zup.order.service;

import br.com.zup.order.controller.order.request.CreateOrderRequest;
import br.com.zup.order.controller.order.response.OrderResponse;
import br.com.zup.order.controller.status.request.StatusOrderRequest;

import java.util.List;

public interface OrderService {

    String save(CreateOrderRequest request);

    List<OrderResponse> findAll();

    void updateStatusOrder(StatusOrderRequest request);

}
