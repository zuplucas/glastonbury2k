package br.com.zup.payment.service.impl;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.enums.PaymentStatus;
import br.com.zup.payment.event.PaymentCallbackEvent;
import br.com.zup.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final KafkaTemplate<String, PaymentCallbackEvent> template;

  @Override
  public void process(PaymentRequest paymentRequest) {
    PaymentCallbackEvent paymentCallbackEvent =
        new PaymentCallbackEvent(paymentRequest.getOrderId(), paymentRequest.getAmount());
    if (paymentRequest.getAmount().doubleValue() > 100) {
      paymentCallbackEvent.setPaymentStatus(PaymentStatus.REJECTED);
    }
    System.out.println(paymentCallbackEvent);
    this.template.send("payment-callback", paymentCallbackEvent);
  }
}
