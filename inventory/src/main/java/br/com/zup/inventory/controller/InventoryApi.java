package br.com.zup.inventory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.inventory.controller.request.BookRequest;

public interface InventoryApi {

    @PostMapping("/booking")
    void book(@RequestBody BookRequest request);
}
