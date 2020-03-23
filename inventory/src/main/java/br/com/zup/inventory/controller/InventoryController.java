package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.UndoBookRequest;
import br.com.zup.inventory.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi{

    private BookService bookService;

    @Autowired
    public InventoryController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void book(@RequestBody BookRequest request) {
        this.bookService.book(request);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void undo(@RequestBody UndoBookRequest request) {
        this.bookService.undo(request);
    }
}
