package br.com.zup.order.orchestrator.integration.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusOrderFeignConfig {

    private String orderUrl;
    private ObjectMapper objectMapper;

    public StatusOrderFeignConfig(@Value(value = "${order.url}") String orderUrl,
                                  ObjectMapper objectMapper) {
        this.orderUrl = orderUrl;
        this.objectMapper = objectMapper;
    }


    @Bean
    public StatusOrderApi statusOrderApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .logLevel(feign.Logger.Level.FULL)
                .target(StatusOrderApi.class, orderUrl);

    }
}
