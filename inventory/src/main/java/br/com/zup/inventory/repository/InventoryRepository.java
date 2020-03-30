package br.com.zup.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.inventory.entity.Inventory;
import br.com.zup.inventory.enumeration.WeekDay;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Inventory findByDay(WeekDay day);

}
