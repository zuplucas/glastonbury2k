package br.com.zup.order.integration.orchestrator;

import br.com.zup.order.integration.orchestrator.request.StartFlowRequest;
import br.com.zup.order.integration.orchestrator.response.StartFlowResponse;
import feign.Headers;
import feign.RequestLine;

public interface OrderOrchestratorApi {

    @RequestLine("POST /order-flow")
    @Headers("Content-Type: application/json")
    StartFlowResponse startFlow(StartFlowRequest request);
}
