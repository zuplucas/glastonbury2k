package br.com.zup.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.service.InventoryService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void book(@RequestBody BookRequest request) {
        inventoryService.processBooking(request);
        System.out.println(request.getOrderEntries());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/unbook/{orderId}")
    public void rejectOrder(@PathVariable String orderId) {
        inventoryService.unbook(orderId);
    }

}
