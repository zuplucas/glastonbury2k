package br.com.zup.order.orchestrator.orchestration;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Getter
@FieldNameConstants
public abstract class CamundaStep implements JavaDelegate {

  @FieldNameConstants.Exclude private DelegateExecution delegateExecution;

  public abstract void execute();

  @Override
  public void execute(DelegateExecution delegateExecution) {
    this.delegateExecution = delegateExecution;
    execute();
  }
}
