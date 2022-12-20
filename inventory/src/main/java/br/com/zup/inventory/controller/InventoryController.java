package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi{

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void book(@RequestBody BookRequest request) {
        System.out.println(request.getOrderEntries());
    }
}
