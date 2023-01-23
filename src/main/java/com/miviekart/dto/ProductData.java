package com.miviekart.dto;

import com.miviekart.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor

public class ProductData {
    private int productId;
    private String productName;
    private int ProductQuantity;
    private double ProductPrice;
    private String imageUrl;

    public ProductData() {
        super();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductData [productId=" + productId + ", productName=" + productName + ", ProductQuantity="
                + ProductQuantity + ", ProductPrice=" + ProductPrice + ", imageUrl=" + imageUrl + "]";
    }

}
