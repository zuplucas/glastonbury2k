package br.com.zup.payment.controller.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
  private String orderId;
  private BigDecimal amount;
}
