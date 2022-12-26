package br.com.zup.order.orchestrator.controller.request;

import java.math.BigDecimal;
import java.util.Map;

public record StartFlowRequest(String orderId, String customerId, BigDecimal amount, Map<String,Integer> items){

}