package br.com.zup.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.payment.controller.request.BookRequest;
import br.com.zup.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController implements PaymentApi {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/paymenting", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void book(@RequestBody BookRequest request) {
		System.out.println("Payment Paymenting book");
		System.out.println(request.getOrderEntries());
		System.out.println("paymenting");
		System.out.println(request);
		paymentService.payment(request);
	}
}
