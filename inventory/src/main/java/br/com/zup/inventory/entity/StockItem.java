package br.com.zup.inventory.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "stockitem")
public class StockItem {

    @Id
    private String id;

    private String idItem;

    private Integer quantityStock;


    public StockItem() {
    }

    public StockItem(String id, String idItem, Integer quantityStock) {
        this.id = id;
        this.idItem = idItem;
        this.quantityStock = quantityStock;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }
}
