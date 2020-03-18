package br.com.zup.inventory.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private KafkaTemplate<String, Map<String, String>> template;

    @Autowired
    public InventoryServiceImpl(KafkaTemplate<String, Map<String, String>> template) {
        this.template = template;
    }

	@Override
	public void orderPurchase(BookRequest request) {

		this.template.send("payment-event", request.getOrderEntries());
	}
}
