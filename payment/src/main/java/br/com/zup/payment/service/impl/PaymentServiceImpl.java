package br.com.zup.payment.service.impl;

import br.com.zup.payment.controller.request.PaymentRequest;
import br.com.zup.payment.event.PaymentEvent;
import br.com.zup.payment.event.PaymentStatus;
import br.com.zup.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private KafkaTemplate<String, PaymentEvent> paymentTpl;

    @Autowired
    public PaymentServiceImpl(KafkaTemplate<String, PaymentEvent> paymentTpl) {
        this.paymentTpl = paymentTpl;
    }


    @Override
    public void process(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent;
        boolean rejected = false;
        if(paymentRequest.getAmount().compareTo(new BigDecimal(100)) > 0) {
            //REJECTED
            rejected = true;
            paymentEvent = new PaymentEvent(paymentRequest.getOrderId(), PaymentStatus.PAYMENT_REJECTED, paymentRequest.getAmount());
            System.out.println("Payment Rejected. Amount: " + paymentRequest.getAmount());
        } else {
            //ACCEPTED
            paymentEvent = new PaymentEvent(paymentRequest.getOrderId(), PaymentStatus.PAYMENT_APPROVED, paymentRequest.getAmount());
            System.out.println("Payment Approved. Amount: " + paymentRequest.getAmount());
        }
        this.paymentTpl.send("payment-event", paymentEvent);
    }
}
