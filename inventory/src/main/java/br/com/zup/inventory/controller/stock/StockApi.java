package br.com.zup.inventory.controller.stock;

import br.com.zup.inventory.controller.stock.request.StockRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockApi {

        @PostMapping("/stockitem")
        void stock(@RequestBody StockRequest request);

}
