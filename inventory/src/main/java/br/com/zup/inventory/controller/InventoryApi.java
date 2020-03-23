package br.com.zup.inventory.controller;

import br.com.zup.inventory.controller.request.BookRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InventoryApi {

  @PostMapping("/booking")
  void booking(@RequestBody BookRequest request) throws Exception, Exception;

  @DeleteMapping("/unbooking")
  void unbooking(@RequestBody BookRequest request) throws Exception, Exception;
}
