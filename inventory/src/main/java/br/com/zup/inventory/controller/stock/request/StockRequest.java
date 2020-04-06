package br.com.zup.inventory.controller.stock.request;


public class StockRequest {

    private String idItem;

    private Integer quantityImplement;


    public StockRequest(){}

    public StockRequest(String idItem, Integer quantityImplement) {
        this.idItem = idItem;
        this.quantityImplement = quantityImplement;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantityImplement() {
        return quantityImplement;
    }

    public void setQuantityImplement(Integer quantityImplement) {
        this.quantityImplement = quantityImplement;
    }
}
