package com.miviekart.dto;

import com.miviekart.model.Product;

public class OrderItemData {
    private int itemId;
  
    private Product product;

    public OrderItemData() {
        super();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItemData [itemId=" + itemId + ", product=" + product + "]";
    }


    

}
