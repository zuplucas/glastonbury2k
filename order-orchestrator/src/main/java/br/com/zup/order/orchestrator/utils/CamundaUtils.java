package br.com.zup.order.orchestrator.utils;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public abstract class CamundaUtils {

    protected String getRequiredValue(DelegateExecution delegateExecution, String key) {
        String value = (String) delegateExecution.getVariable(key);
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("Key não encontrada");

        return value;
    }

}
