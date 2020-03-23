package br.com.zup.order.orchestrator.integration.order.request;

import br.com.zup.order.orchestrator.enums.OrderStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest implements Serializable {
  private static final long serialVersionUID = 676106439910301511L;
  private String orderId;
  private OrderStatus orderStatus;
}
