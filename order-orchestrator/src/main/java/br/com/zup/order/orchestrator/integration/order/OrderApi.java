package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.OrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

  @RequestLine("POST /persist")
  @Headers("Content-Type: application/json")
  void updateOrder(OrderRequest request);
}
