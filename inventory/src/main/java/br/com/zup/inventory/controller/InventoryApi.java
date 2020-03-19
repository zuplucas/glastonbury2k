package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.ReverseStockRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface InventoryApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    void book(@RequestBody BookRequest request);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/reverse", consumes = MediaType.APPLICATION_JSON_VALUE)
    void reverseStock(
            ReverseStockRequest request);
}
