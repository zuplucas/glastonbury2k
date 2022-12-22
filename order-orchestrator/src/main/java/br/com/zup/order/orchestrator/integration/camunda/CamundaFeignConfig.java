package br.com.zup.order.orchestrator.integration.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaFeignConfig {

    private final String camundaUrl;
    private final ObjectMapper objectMapper;

    public CamundaFeignConfig(@Value(value = "${camunda.bpm.client.base-url}") String camundaUrl,
                              ObjectMapper objectMapper) {
        this.camundaUrl = camundaUrl;
        this.objectMapper = objectMapper;
    }

    @Bean
    public CamundaApi camundaApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .logLevel(feign.Logger.Level.FULL)
                .target(CamundaApi.class, camundaUrl);

    }
}
