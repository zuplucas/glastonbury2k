package br.com.zup.payment.service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.stereotype.Service;

import br.com.zup.payment.event.OrderUpdateEvent;
import br.com.zup.payment.event.PaymentUpdateEvent;
import br.com.zup.payment.request.PaymentRequest;

@Service
public class PaymentService {

    private KafkaTemplate<String, OrderUpdateEvent> orderTemplate;
    private KafkaTemplate<String, PaymentUpdateEvent> paymentTemplate;

    public PaymentService(
            KafkaTemplate<String, OrderUpdateEvent> orderTemplate,
            KafkaTemplate<String, PaymentUpdateEvent> paymentTemplate) {
        this.orderTemplate = orderTemplate;
        this.paymentTemplate = paymentTemplate;
    }

    public void pay(PaymentRequest request) {
        OrderUpdateEvent event = new OrderUpdateEvent(request.getOrderId(), "PAYMENT_REQUESTED");
        this.orderTemplate.send("update-order-events", event);

        PaymentUpdateEvent paymentUpdateEvent = new PaymentUpdateEvent();
        paymentUpdateEvent.setOrderId(request.getOrderId());

        //Toda vez que o valor da compra for 1, o pagamento será rejeitado
        if (request.getAmount().equals(BigDecimal.ONE)) {
            paymentUpdateEvent.setStatus("PAYMENT_REJECTED");
        } else {
            //After ThirdPartyPaymentApiCall
            paymentUpdateEvent.setStatus("PAYMENT_ACCEPTED");
        }

        this.paymentTemplate.send("payment-events", paymentUpdateEvent);
    }
}
