package br.com.zup.inventory.controller.stock;

import br.com.zup.inventory.controller.stock.request.StockRequest;
import br.com.zup.inventory.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stockitem")
public class StockController implements StockApi {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void stock(@RequestBody StockRequest request) {
        stockService.save(request);


    }
}
