package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController implements InventoryApi {

  private InventoryService inventoryService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void booking(@RequestBody BookRequest request) throws Exception {
    this.inventoryService.bookTicket(request);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping(value = "/unbooking", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void unbooking(@RequestBody BookRequest request) throws Exception {
    this.inventoryService.unbookTicket(request);
  }
}
