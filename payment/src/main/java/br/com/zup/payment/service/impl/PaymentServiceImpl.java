package br.com.zup.payment.service.impl;

import br.com.zup.payment.event.OrderCreatedEvent;
import br.com.zup.payment.event.PaymentEvent;
import br.com.zup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    private KafkaTemplate<String, PaymentEvent> template;

    @Autowired
    public PaymentServiceImpl(KafkaTemplate<String, PaymentEvent> template) {
        this.template = template;
    }

    @Override
    public void pay(OrderCreatedEvent order) {
        PaymentEvent paymentEvent = new PaymentEvent(order);
        paymentEvent.setPaymentApproved(new Random().nextInt(5) >= 3);
        this.template.send("payment-event", paymentEvent);
    }
}
