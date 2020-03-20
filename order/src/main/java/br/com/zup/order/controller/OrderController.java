package br.com.zup.order.controller;

import br.com.zup.order.controller.request.*;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.service.OrderService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderResponse> getOrders() {
        return this.orderService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/reject-order")
    public void rejectOrder(@RequestBody RejectOrderRequest rejectOrderRequest) {
        System.out.println("RejectOrder: " + rejectOrderRequest.getOrderId() + " - " + rejectOrderRequest.getRejectReason().toString());
        if(rejectOrderRequest.getRejectReason() == RejectOrderReason.NO_AVAILABILITY) {
            this.orderService.updateStatus(rejectOrderRequest.getOrderId(), "rejected");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/finish-order")
    public void finishOrder(@RequestBody FinishOrderRequest finishOrderRequest) {
        System.out.println("FinishOrder: " + finishOrderRequest.getOrderId());
        this.orderService.updateStatus(finishOrderRequest.getOrderId(), "payment-confirmed");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/booked")
    public void booked(@RequestBody BookedOrderRequest bookedOrderRequest) {
        System.out.println("BookedOrder: " + bookedOrderRequest.getOrderId());
        this.orderService.updateStatus(bookedOrderRequest.getOrderId(), "booked");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/reject-payment-order")
    public void rejectPaymentOrder(@RequestBody RejectPaymentOrderRequest rejectPaymentOrderRequest) {
        System.out.println("RejectPaymentOrder: " + rejectPaymentOrderRequest.getOrderId());
        this.orderService.updateStatus(rejectPaymentOrderRequest.getOrderId(), "payment-rejected");
    }
}
