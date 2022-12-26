package br.com.zup.order.orchestrator.domain;

import java.math.BigDecimal;
import java.util.Map;

public record WorkflowOrderVariable(String orderId, String customerId, BigDecimal amount, Map<String, Integer> items) {

}