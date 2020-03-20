package br.com.zup.order.orchestrator.integration.inventory.errors;

public class NoAvailabilityException extends RuntimeException {

    public NoAvailabilityException(String message) {
        super(message);
    }

}
