package br.com.zup.order.orchestrator.integration;

import br.com.zup.order.orchestrator.integration.request.BookRequest;
import feign.Headers;
import feign.RequestLine;

public interface PaymentApi {

	@RequestLine("POST /paymenting")
	@Headers("Content-Type: application/json")
	public void paymenting(BookRequest request);
}
