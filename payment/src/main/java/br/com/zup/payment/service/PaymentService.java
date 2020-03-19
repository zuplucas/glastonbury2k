package br.com.zup.payment.service;

import br.com.zup.payment.event.OrderCreatedEvent;

public interface PaymentService {

    void pay(OrderCreatedEvent order);
}
