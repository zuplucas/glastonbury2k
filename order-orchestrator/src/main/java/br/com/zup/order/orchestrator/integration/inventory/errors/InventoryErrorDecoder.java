package br.com.zup.order.orchestrator.integration.inventory.errors;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;

public class InventoryErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 422) {
            return new NoAvailabilityException (
                    //response.status(),
                    "No tickets available"
            );
        }
        return new RuntimeException("General exception");
    }
}
