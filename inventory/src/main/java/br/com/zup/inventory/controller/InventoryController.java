package br.com.zup.inventory.controller;

import br.com.zup.inventory.constants.StockOperationConstants;
import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.ReverseStockRequest;
import br.com.zup.inventory.service.book.BookService;
import br.com.zup.inventory.service.stock.StockService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi {

    private static final String BOOK_NOT_SUCCESS = "book-not-success";
    private BookService bookService;
    private StockService stockService;

    @Autowired
    public InventoryController(BookService bookService, StockService stockService) {
        this.bookService = bookService;
        this.stockService = stockService;
    }

    @Override
    public void book(@RequestBody BookRequest request) {
        if (!bookService.book(request)) {
            throw new BpmnError(BOOK_NOT_SUCCESS);
        }
    }

    @Override
    public void reverseStock(ReverseStockRequest request) {
        stockService.updateStock(request.getOrder(), StockOperationConstants.STOCK_REVERSAL);
    }
}
