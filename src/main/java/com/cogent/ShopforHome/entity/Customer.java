package com.cogent.ShopforHome.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Customer extends User {
    private double discountPercentage=0;
    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);

    }
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ShoppingCart cart;

    @OneToMany
    @JoinTable(name="orders")
    private List<Order> orderList;

    @ManyToMany
    @JoinTable(name = "favorites")
    private List<Product> productWishList;


    public Customer() {

    }

    public List<Order> getOrderList() {
        return orderList;
    }
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Product> getProductWishList() {
        return productWishList;
    }

    public void setProductWishList(List<Product> productWishList) {
        this.productWishList = productWishList;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

//    public List<Product> getProductWishListIds() {
//        return productWishList;
//    }

//    public void setProductWishListIds(List<Product> productWishListIds) {
//        this.productWishList = productWishListIds;
//    }

//    public List<Product> getProductWishList() {
//        return productWishList;
//    }

//    public void setProductWishList(List<Product> productWishList) {
//        this.productWishList = productWishList;
//    }
//
//    public void addProductWishListIds(Product p){
//        productWishList.add(p);
//    }
//    public void removeProductWishListIds(Product p){
//        productWishList.remove(p);
//    }

}
