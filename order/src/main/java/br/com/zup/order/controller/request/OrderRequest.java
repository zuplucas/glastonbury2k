package br.com.zup.order.controller.request;

import br.com.zup.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
  private String orderId;
  private OrderStatus orderStatus;
}
