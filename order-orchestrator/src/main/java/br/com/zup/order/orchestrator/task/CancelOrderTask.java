package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.utils.CamundaUtils;

@Component
public class  CancelOrderTask extends CamundaUtils implements JavaDelegate {

    private OrderApi orderApi;

    public CancelOrderTask(OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderId = getRequiredValue(delegateExecution, "ORDER-ID");
        orderApi.cancel(orderId);
    }

}
