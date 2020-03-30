package br.com.zup.order.service;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {

    String save(CreateOrderRequest request);

    List<OrderResponse> find();

    void cancelOrder(String orderId) throws NotFoundException;

    void completeOrder(String orderId) throws NotFoundException;

    void rejectOrder(String orderId) throws NotFoundException;
}
