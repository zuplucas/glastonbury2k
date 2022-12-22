package br.com.zup.order.orchestrator.integration.camunda;

import br.com.zup.order.orchestrator.integration.camunda.request.CorrelationMessageRequest;
import br.com.zup.order.orchestrator.integration.camunda.request.StartProcessRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CamundaApi {
    @RequestLine("POST /process-definition/key/{processDefKey}/start")
    @Headers("Content-Type: application/json")
    void startProcess(@Param("processDefKey") String processDefKey, StartProcessRequest request);

    @RequestLine("POST /process-instance/message-async")
    @Headers("Content-Type: application/json")
    void sendCorrelationMessage(CorrelationMessageRequest request);
}
