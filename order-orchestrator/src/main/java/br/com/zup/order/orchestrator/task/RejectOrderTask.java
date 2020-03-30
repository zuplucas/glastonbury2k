package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.utils.CamundaUtils;

@Component
public class RejectOrderTask extends CamundaUtils implements JavaDelegate {

    private OrderApi orderApi;

    @Autowired
    public RejectOrderTask(OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderId = getRequiredValue(delegateExecution, "ORDER-ID");
        this.orderApi.rejectOrder(orderId);
    }
}
