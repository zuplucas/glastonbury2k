package br.com.zup.inventory.repository;


import br.com.zup.inventory.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, String> {

    StockItem findByIdItem(String idItem);
}
