package br.com.zup.order.orchestrator.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class PaymentFeignConfig {

    private String paymentUrl;
    private ObjectMapper objectMapper;

    public PaymentFeignConfig(@Value(value = "${payment.url}") String paymentUrl,
            ObjectMapper objectMapper) {
        this.paymentUrl = paymentUrl;
        this.objectMapper = objectMapper;
    }


    @Bean
    public PaymentApi paymentApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .logLevel(feign.Logger.Level.FULL)
                .target(PaymentApi.class, paymentUrl);

    }
}
