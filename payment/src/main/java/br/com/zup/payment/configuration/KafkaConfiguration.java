package br.com.zup.payment.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.zup.payment.event.OrderUpdateEvent;
import br.com.zup.payment.event.PaymentUpdateEvent;


@Configuration
public class KafkaConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        return new KafkaAdmin(configs);
    }

    @Bean
    public DefaultKafkaProducerFactory messageProducerFactory() {

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PaymentUpdateEvent> paymentKafkaTemplate() {
        return new KafkaTemplate<String, PaymentUpdateEvent>(messageProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, OrderUpdateEvent> orderKafkaTemplate() {
        return new KafkaTemplate<String, OrderUpdateEvent>(messageProducerFactory());
    }


}
