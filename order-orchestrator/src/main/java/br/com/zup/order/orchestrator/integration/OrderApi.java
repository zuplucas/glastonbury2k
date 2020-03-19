package br.com.zup.order.orchestrator.integration;

import br.com.zup.order.orchestrator.integration.request.BookRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

	@RequestLine("POST /finish-order")
	@Headers("Content-Type: application/json")
	public void finishOrder(BookRequest request);

	@RequestLine("POST /cancel-order")
	@Headers("Content-Type: application/json")
	public void cancelOrder(BookRequest request);

}
