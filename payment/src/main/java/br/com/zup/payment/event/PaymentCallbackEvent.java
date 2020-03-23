package br.com.zup.payment.event;

import br.com.zup.payment.enums.PaymentStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentCallbackEvent implements Serializable {

  public PaymentCallbackEvent(String orderId, BigDecimal amount) {
    this.orderId = orderId;
    this.amount = amount;
  }

  private static final long serialVersionUID = -4887056994050749100L;
  private String orderId;
  private BigDecimal amount;
  private PaymentStatus paymentStatus = PaymentStatus.APPROVED;
}
