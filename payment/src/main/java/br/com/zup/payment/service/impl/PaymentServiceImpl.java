package br.com.zup.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.payment.controller.request.BookRequest;
import br.com.zup.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

//    private OrderRepository orderRepository;
	private KafkaTemplate<String, BookRequest> template;

	@Autowired
	public PaymentServiceImpl(KafkaTemplate<String, BookRequest> template) {
//        this.orderRepository = orderRepository;
		this.template = template;
	}

	@Override
	public String payment(BookRequest request) {
//        String orderId = this.orderRepository.save(request.toEntity()).getId();
//
//        OrderCreatedEvent event = new OrderCreatedEvent(
//                orderId,
//                request.getCustomerId(),
//                request.getAmount(),
//                createItemMap(request)
//        );

		this.template.send("payment-event", request);

		return "";
	}

}
