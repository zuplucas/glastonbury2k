package br.com.zup.payment.controller;

import br.com.zup.payment.controller.request.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentApi {

  @PostMapping("/pay")
  void pay(@RequestBody PaymentRequest paymentRequest);
}
