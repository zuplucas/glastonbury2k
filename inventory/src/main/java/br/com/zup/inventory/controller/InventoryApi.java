package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.UnbookRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.inventory.controller.request.BookRequest;

public interface InventoryApi {

    @PostMapping("/booking")
    void book(@RequestBody BookRequest request) throws Exception;

    @PostMapping("/unbooking")
    void unbook(@RequestBody UnbookRequest request);

}
