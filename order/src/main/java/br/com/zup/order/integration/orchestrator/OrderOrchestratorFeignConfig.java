package br.com.zup.order.integration.orchestrator;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderOrchestratorFeignConfig {

    private final String orchestatorUrl;
    private final ObjectMapper objectMapper;

    public OrderOrchestratorFeignConfig(@Value(value = "${orchestrator.url}") String orchestatorUrl,
                                        ObjectMapper objectMapper) {
        this.orchestatorUrl = orchestatorUrl;
        this.objectMapper = objectMapper;
    }


    @Bean
    public OrderOrchestratorApi paymentApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .logLevel(feign.Logger.Level.FULL)
                .target(OrderOrchestratorApi.class, orchestatorUrl);

    }
}
