package br.com.zup.order.service;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.OrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.exception.NotFoundOrderException;
import java.util.List;

public interface OrderService {

  String save(CreateOrderRequest request);

  void save(OrderRequest request) throws NotFoundOrderException;

  List<OrderResponse> findAll();

  OrderResponse findById(String id);
}
