package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.UndoBookRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InventoryApi {

    @PostMapping("/booking")
    void book(@RequestBody BookRequest request);

    @DeleteMapping("/booking")
    void undo(@RequestBody UndoBookRequest request);
}
