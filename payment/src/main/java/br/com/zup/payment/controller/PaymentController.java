package br.com.zup.payment.controller;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController implements PaymentApi {

  private final PaymentService paymentService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void pay(@RequestBody PaymentRequest paymentRequest) {
    this.paymentService.process(paymentRequest);
  }
}
