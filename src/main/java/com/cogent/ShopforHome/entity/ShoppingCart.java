package com.cogent.ShopforHome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private long CartId;
    @Transient
   // @OneToMany( cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore  // Ignore the customer property during JSON serialization
    private Customer customer;
    public ShoppingCart() {
    }

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }
    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
    }
    public long getCartId() {
        return CartId;
    }

    public void setCartId(long cartId) {
        CartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}
