package br.com.zup.order.controller;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.service.OrderService;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody CreateOrderRequest request) {
        return this.orderService.save(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderResponse> getOrders() {
        return this.orderService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/cancel-order/{orderId}")
    public void cancelOrder(@PathVariable String orderId) throws NotFoundException {
        orderService.cancelOrder(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/complete/{orderId}")
    public void completeOrder(@PathVariable String orderId) throws NotFoundException {
        orderService.completeOrder(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/reject/{orderId}")
    public void rejectOrder(@PathVariable String orderId) throws NotFoundException {
        orderService.rejectOrder(orderId);
    }

}
