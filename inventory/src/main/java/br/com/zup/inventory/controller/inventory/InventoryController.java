package br.com.zup.inventory.controller.inventory;

import br.com.zup.inventory.controller.inventory.request.BookRequest;
import br.com.zup.inventory.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi {


    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void book(@RequestBody BookRequest request) throws Exception {
        if(!inventoryService.save(request)){
            throw  new Exception(String.valueOf("404"));
        }
    }
}
