package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.utils.CamundaUtils;

@Component
public class UnbookTicketTask extends CamundaUtils implements JavaDelegate {

    private InventoryApi inventoryApi;

    @Autowired
    public UnbookTicketTask(InventoryApi inventoryApi) {
        this.inventoryApi = inventoryApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderId = getRequiredValue(delegateExecution, "ORDER-ID");
        this.inventoryApi.unbookTickets(orderId);
    }
}
