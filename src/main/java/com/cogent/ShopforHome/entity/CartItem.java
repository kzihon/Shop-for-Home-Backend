package com.cogent.ShopforHome.entity;

import jakarta.persistence.*;

//@Entity
public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long productId; // Store the product ID directly

    private int quantity;

    // Getters and setters...

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}