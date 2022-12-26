package br.com.zup.order.orchestrator.service;

import br.com.zup.order.orchestrator.domain.WorkflowOrderVariable;
import br.com.zup.order.orchestrator.integration.camunda.CamundaApi;
import br.com.zup.order.orchestrator.integration.camunda.request.StartProcessRequest;
import br.com.zup.order.orchestrator.integration.camunda.request.VariableItem;
import br.com.zup.order.orchestrator.integration.camunda.response.StartProcessResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WorkflowStarterService {
    private final ObjectMapper objectMapper;
    private final CamundaApi camundaApi;

    public WorkflowStarterService(ObjectMapper objectMapper, CamundaApi camundaApi) {
        this.objectMapper = objectMapper;
        this.camundaApi = camundaApi;
    }

    public String startWorkflowProcess(WorkflowOrderVariable order) {
        Map<String, VariableItem> variables = new HashMap<>();

        try {
            String orderJson = objectMapper.writeValueAsString(order);

            variables.put("ORDER", new VariableItem(orderJson, ValueType.STRING.getName()));

            StartProcessRequest request = new StartProcessRequest(variables,
                    "ORDER-" + order.orderId());

            System.out.println(objectMapper.writeValueAsString(request));
            StartProcessResponse camundaResponse = camundaApi.startProcess("order-process", request);

            return camundaResponse.id();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
