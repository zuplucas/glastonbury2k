package br.com.zup.order.orchestrator.integration.camunda.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @param messageName {
 *                    "messageName": "payment_callback",
 *                    "processInstanceQuery": {
 *                    "businessKey": "ORDER-aa41e696-a0df-47fb-9ee8-09c481f7559f"
 *                    },
 *                    "variables": {
 *                    "myVariableName": {
 *                    "value": "myStringValue"
 *                    }
 *                    }
 *                    }
 */
public record CorrelationMessageRequest(String messageName, Map<String, String> processInstanceQuery,
                                        Map<String, VariableItem> variables) {

    public static CorrelationMessageRequest fromBusinessKey(String messageName, String businessKey, Map<String, VariableItem> variables) {
        Map<String, String> processInstanceQuery = new HashMap<>();
        processInstanceQuery.put("businessKey", businessKey);
        return new CorrelationMessageRequest(messageName, processInstanceQuery, variables);
    }
}
