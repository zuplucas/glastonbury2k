package br.com.zup.order.orchestrator.controller;

import br.com.zup.order.orchestrator.controller.request.StartFlowRequest;
import br.com.zup.order.orchestrator.controller.response.StartFlowResponse;
import br.com.zup.order.orchestrator.domain.WorkflowOrderVariable;
import br.com.zup.order.orchestrator.service.WorkflowStarterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-flow")
public class StartFlowController {

    private final WorkflowStarterService workflowStarterService;

    public StartFlowController(WorkflowStarterService workflowStarterService) {
        this.workflowStarterService = workflowStarterService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public StartFlowResponse start(@RequestBody StartFlowRequest request) {
        WorkflowOrderVariable order = new WorkflowOrderVariable(
                request.orderId(), request.customerId(), request.amount(), request.items()
        );
        String processId = this.workflowStarterService.startWorkflowProcess(order);

        return new StartFlowResponse(order.orderId(), processId);
    }
}
