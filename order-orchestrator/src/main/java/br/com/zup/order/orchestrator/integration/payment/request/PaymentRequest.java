package br.com.zup.order.orchestrator.integration.payment.request;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest implements Serializable {
  private static final long serialVersionUID = -2205874785838911276L;
  private String orderId;
  private BigDecimal amount;
}
