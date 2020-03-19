package br.com.zup.inventory.service.stock.impl;

import br.com.zup.inventory.constants.StockOperationConstants;
import br.com.zup.inventory.controller.stock.request.StockRequest;
import br.com.zup.inventory.controller.stock.response.StockResponse;
import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.entity.ticket.Ticket;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.inventory.repository.stock.StockRepository;
import br.com.zup.inventory.service.stock.StockService;

import javax.print.attribute.standard.OrientationRequested;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.zup.inventory.constants.StockOperationConstants.STOCK_LOW;
import static br.com.zup.inventory.constants.StockOperationConstants.STOCK_REVERSAL;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;
    private TicketService ticketService;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, TicketService ticketService) {
        this.stockRepository = stockRepository;
        this.ticketService = ticketService;
    }

    @Override
    public String save(StockRequest request) {
        Optional<Ticket> ticket = ticketService.getById(request.getTicketId());
        if (!ticket.isPresent()) {
            return "Ticket "+ request.getTicketId() + " not exist!";
        }
        return this.stockRepository.save(request.toEntity()).getId();
    }

    @Override
    public List<StockResponse> findAll() {
        return this.stockRepository.findAll()
                .stream()
                .map(StockResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Stock> getStockByTicket(String ticketId) {
        Optional<Ticket> ticket = ticketService.getById(ticketId);
        if (!ticket.isPresent()) {
            return Optional.empty();
        }
        return stockRepository.findByTicket(ticket.get());
    }

    @Override
    public boolean updateStock(OrderCreatedEvent order, StockOperationConstants type) {
        return order.getItems().stream().anyMatch(item -> updateStock(item.getIdTicket(), item.getQuantity(), type));
    }

    @Override
    public boolean updateStock(String ticketId, Integer quantity, StockOperationConstants type) {
        Optional<Stock> stockByTicket = getStockByTicket(ticketId);
        Integer quantityStock = stockByTicket.get().getQuantity();

        if (STOCK_LOW.equals(type)) {
            if (quantityStock < quantity) {
                return false;
            }
            stockByTicket.get().setQuantity(quantityStock - quantity);
        } else if (STOCK_REVERSAL.equals(type))  {
            stockByTicket.get().setQuantity(quantityStock + quantity);
        }
        this.stockRepository.save(stockByTicket.get());
        return true;
    }

}
