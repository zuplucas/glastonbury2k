package br.com.zup.order.orchestrator.orchestration.order;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.orchestration.CamundaStep;
import br.com.zup.order.orchestrator.orchestration.CamundaVariable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public abstract class OrderStep extends CamundaStep {

  protected final CamundaVariable<OrderCreatedEvent> order =
      CamundaVariable.build(this, Fields.order, new ObjectMapper(), OrderCreatedEvent.class);
}
