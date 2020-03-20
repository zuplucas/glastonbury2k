package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.errors.InventoryErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class InventoryFeignConfig {

    private String inventoryUrl;
    private ObjectMapper objectMapper;

    public InventoryFeignConfig(@Value(value = "${inventory.url}") String inventoryUrl,
            ObjectMapper objectMapper) {
        this.inventoryUrl = inventoryUrl;
        this.objectMapper = objectMapper;
    }


    @Bean
    public InventoryApi inventoryApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new InventoryErrorDecoder())
                .logLevel(feign.Logger.Level.FULL)
                .target(InventoryApi.class, inventoryUrl);

    }
}
