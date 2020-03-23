package br.com.zup.order.orchestrator.orchestration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public final class CamundaVariable<T> {

  private final CamundaStep camundaStep;
  private final String name;
  private T valueOnMemory;
  private ObjectMapper objectMapper;
  private Class<T> clazz;

  private CamundaVariable(
      CamundaStep camundaStep, String name, ObjectMapper objectMapper, Class<T> clazz) {
    this.camundaStep = camundaStep;
    this.name = name;
    this.objectMapper = objectMapper;
    this.clazz = clazz;
  }

  public void set(T newValue) {
    this.camundaStep.getDelegateExecution().setVariable(this.name, newValue);
    this.valueOnMemory = newValue;
  }

  public Optional<T> getOptional() {
    //    if (this.valueOnMemory != null) {
    //      return Optional.of(this.valueOnMemory);
    //    }

    //    T valueOnCamunda = (T) this.camundaStep.getDelegateExecution().getVariable(this.name);
    String value = (String) this.camundaStep.getDelegateExecution().getVariable(this.name);
    T valueOnCamunda = null;
    try {
      valueOnCamunda = (T) this.objectMapper.readValue(value, this.clazz);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.valueOnMemory = valueOnCamunda;
    return Optional.ofNullable(valueOnCamunda);
  }

  public T get() {
    return this.getOptional()
        .orElseThrow(
            () -> {
              String message =
                  String.format("Valor da variável '%s' não encontrado no Camunda", this.name);
              return new RuntimeException(message);
            });
  }

  public boolean exists() {
    return this.getOptional().isPresent();
  }

  public static <U extends Serializable> CamundaVariable<U> build(
      CamundaStep camundaStep, String name, ObjectMapper objectMapper, Class<U> clazz) {
    return new CamundaVariable<>(camundaStep, name, objectMapper, clazz);
  }
}
