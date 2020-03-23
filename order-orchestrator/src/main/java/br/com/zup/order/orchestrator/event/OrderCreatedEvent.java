package br.com.zup.order.orchestrator.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent implements Serializable {

  private static final long serialVersionUID = 5073790346363466124L;
  private String orderId;
  private String customerId;
  private BigDecimal amount;
  private Map<String, Integer> items;
}
