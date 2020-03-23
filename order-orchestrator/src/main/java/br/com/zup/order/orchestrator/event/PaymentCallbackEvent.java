package br.com.zup.order.orchestrator.event;

import br.com.zup.order.orchestrator.enums.PaymentStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCallbackEvent implements Serializable {

  private static final long serialVersionUID = -4887056994050749100L;
  private String orderId;
  private BigDecimal amount;
  private PaymentStatus paymentStatus;
}
