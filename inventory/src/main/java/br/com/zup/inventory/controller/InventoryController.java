package br.com.zup.inventory.controller;

import br.com.zup.inventory.BusinessError;
import br.com.zup.inventory.controller.request.UnbookRequest;
import br.com.zup.inventory.exceptions.NoAvailabilityException;
import br.com.zup.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.zup.inventory.controller.request.BookRequest;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi{

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void book(@RequestBody BookRequest request) throws Exception {
        System.out.println("Entries: " + request.getOrderEntries());
        boolean result = inventoryService.checkTicketAvailability(request);
        if(!result) {
            throw new NoAvailabilityException("Tickets not available");
        }
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ NoAvailabilityException.class})
    public BusinessError handleException(NoAvailabilityException exception) {
        return new BusinessError("001", exception.getMessage());
    }

    @PostMapping(value = "/unbooking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void unbook(@RequestBody UnbookRequest request) {
        System.out.println("Entries: " + request.getOrderEntries());
        this.inventoryService.rollbackTicketAvailability(request);
    }
}
