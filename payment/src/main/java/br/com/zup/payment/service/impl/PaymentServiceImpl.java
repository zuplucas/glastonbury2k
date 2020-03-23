package br.com.zup.payment.service.impl;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.event.PaymentEvent;
import br.com.zup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private KafkaTemplate<String, PaymentEvent> template;

    @Autowired
    public PaymentServiceImpl(KafkaTemplate<String, PaymentEvent> template) {
        this.template = template;
    }

    @Override
    public void pay(PaymentRequest paymentRequest) {
        System.out.println("ORDER: " + paymentRequest.getOrderId() + " => PAID");

        //this.template.send("payment-event", new PaymentEvent(paymentRequest.getOrderId(), paymentRequest.getAmount()));

        //TO SIMULATE AN ERROR WITH PAYMENT GATEWAY
        this.template.send("payment-event", new PaymentEvent(paymentRequest.getOrderId(), BigDecimal.ONE));
    }
}
