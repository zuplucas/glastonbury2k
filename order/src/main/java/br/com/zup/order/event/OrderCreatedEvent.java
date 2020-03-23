package br.com.zup.order.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreatedEvent implements Serializable {

  private static final long serialVersionUID = 4894074424069247559L;
  private String orderId;
  private String customerId;
  private BigDecimal amount;
  private Map<String, Integer> items;
}
