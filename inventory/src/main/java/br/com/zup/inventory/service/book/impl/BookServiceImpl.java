package br.com.zup.inventory.service.book.impl;

import br.com.zup.inventory.constants.StockOperationConstants;
import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.entity.ticket.Ticket;
import br.com.zup.inventory.repository.stock.StockRepository;
import br.com.zup.inventory.service.book.BookService;
import br.com.zup.inventory.service.stock.StockService;
import br.com.zup.inventory.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private StockService stockService;
    private TicketService ticketService;

    @Autowired
    public BookServiceImpl(TicketService ticketService, StockService stockService) {
        this.stockService = stockService;
        this.ticketService = ticketService;
    }


    @Override
    public boolean book(BookRequest request) {
        return request.getOrderEntries().stream().anyMatch(item ->
            stockService.updateStock(item.getIdTicket(), item.getQuantity(), StockOperationConstants.STOCK_LOW));
    }

}
