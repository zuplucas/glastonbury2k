package br.com.zup.order.orchestrator.controller;

import br.com.zup.order.orchestrator.service.PaymentCallbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback/payment")
public class PaymentCallbackController {
    private final PaymentCallbackService paymentCallbackService;

    public PaymentCallbackController(PaymentCallbackService paymentCallbackService) {
        this.paymentCallbackService = paymentCallbackService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receiveCallback() {
        paymentCallbackService.process("TO BE DEFINED", "DEVERÁ SER O PAYMENT OBJECT");
    }
}
