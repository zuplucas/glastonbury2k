package br.com.zup.order.service;

import java.util.List;

import br.com.zup.order.controller.request.BookRequest;
import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;

public interface OrderService {

    String save(CreateOrderRequest request);

    List<OrderResponse> findAll();

    void finishOrder(BookRequest request);

    void cancelOrder(BookRequest request);

}
