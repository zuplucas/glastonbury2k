package br.com.zup.order.listener;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.configuration.KafkaConfiguration;
import br.com.zup.order.event.UpdateOrderEvent;
import br.com.zup.order.service.impl.OrderServiceImpl;
import javassist.NotFoundException;

@Component
public class UpdateOrderEventsListener {

    private ObjectMapper objectMapper;
    private OrderServiceImpl orderService;

    @Autowired
    public UpdateOrderEventsListener(ObjectMapper objectMapper, OrderServiceImpl orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "update-order-events", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException, NotFoundException {
        UpdateOrderEvent event = this.objectMapper.readValue(message, UpdateOrderEvent.class);

        orderService.updateOrder(event);
    }

}
