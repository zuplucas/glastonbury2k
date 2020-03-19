package br.com.zup.order.controller;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.UpdateStatusOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody CreateOrderRequest request);

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<OrderResponse> getOrders();

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateStatus(UpdateStatusOrderRequest request);
}
