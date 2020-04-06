package br.com.zup.inventory.service.inventory.impl;

import br.com.zup.inventory.controller.inventory.request.BookRequest;
import br.com.zup.inventory.entity.StockItem;
import br.com.zup.inventory.repository.StockItemRepository;
import br.com.zup.inventory.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryServiceImpl implements InventoryService {


    private StockItemRepository stockItemRepository;

    @Autowired
    public InventoryServiceImpl(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    public Boolean save(BookRequest request) {

        StockItem stockItem = new StockItem();
        for (int linhaItem = 0; linhaItem < request.getOrderEntries().size(); linhaItem++) {

            StockItem stockItemBalance =
                    stockItemRepository.findByIdItem
                            (request.getOrderEntries().
                                    get(linhaItem).
                                    getItem());
            if (stockItemBalance == null){
                return false;
            }
            if (stockItemBalance.getQuantityStock()
                    >= request.getOrderEntries().get(linhaItem).getQuantity() ) {
               continue;
            }else{
                return false;
            }
        }
        return true;
    }
}
