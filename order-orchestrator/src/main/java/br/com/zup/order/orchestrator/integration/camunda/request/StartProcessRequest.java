package br.com.zup.order.orchestrator.integration.camunda.request;

import java.util.Map;

public record StartProcessRequest(Map<String, VariableItem> variables, String businessKey) {
}
