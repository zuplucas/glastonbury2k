package br.com.zup.order.orchestrator.integration;

import br.com.zup.order.orchestrator.integration.request.BookRequest;
import feign.Headers;
import feign.RequestLine;

public interface InventoryApi {

	@RequestLine("POST /booking")
	@Headers("Content-Type: application/json")
	public Boolean book(BookRequest request);
}
