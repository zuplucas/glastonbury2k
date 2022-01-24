package br.com.zup.inventory.service.stock.impl;

import br.com.zup.inventory.controller.stock.request.StockRequest;
import br.com.zup.inventory.entity.StockItem;
import br.com.zup.inventory.repository.StockItemRepository;
import br.com.zup.inventory.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class StockServiceImpl implements StockService {


    private StockItemRepository stockItemRepository;

    @Autowired
    public StockServiceImpl(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    public void save(StockRequest request){
        StockItem stockItem = new StockItem();
        stockItem.setId(UUID.randomUUID().toString());
        stockItem.setIdItem(request.getIdItem());
        stockItem.setQuantityStock(request.getQuantityImplement());
        stockItemRepository.save(stockItem);

    }



}
