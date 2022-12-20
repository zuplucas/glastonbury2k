package br.com.zup.order.controller;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.OrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.exception.NotFoundOrderException;
import br.com.zup.order.service.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public String create(@RequestBody CreateOrderRequest request) {
    return this.orderService.save(request);
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<OrderResponse> getOrders() {
    return this.orderService.findAll();
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public OrderResponse getOrder(@PathVariable("id") String id) {
    return this.orderService.findById(id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(path = "/persist", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void persistOrder(@RequestBody OrderRequest orderRequest) throws NotFoundOrderException {
    this.orderService.save(orderRequest);
  }
}
