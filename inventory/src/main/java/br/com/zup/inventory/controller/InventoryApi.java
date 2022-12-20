package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InventoryApi {

    @PostMapping("/booking")
    void book(@RequestBody BookRequest request);
}
