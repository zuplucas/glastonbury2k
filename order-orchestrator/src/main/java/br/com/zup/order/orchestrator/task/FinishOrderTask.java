package br.com.zup.order.orchestrator.task;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FinishOrderTask implements JavaDelegate{

    private ObjectMapper objectMapper;

    public FinishOrderTask(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderItems = (String)delegateExecution.getVariable("ORDER_LIST");
        Map<String, String> event = this.objectMapper.readValue(orderItems, Map.class);
        System.out.println(event.toString());
    }
}
