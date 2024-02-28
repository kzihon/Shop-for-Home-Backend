package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public ShoppingCart createCart(ShoppingCart sc);
    public ShoppingCart updateCart(long id, ShoppingCart p);
    public List<ShoppingCart> getCarts();
    public ShoppingCart getCartById(long id);
    public void deleteCart(long id);



//    public void addProductToCart(long cartId,long productId);
//    public void removeProductFromCart(long cartId, long productId);
//    public void increaseQuntityOfProductInCart(long cartId, long productId,int quantity);
   // public void decreaseQuntityOfProductInCart(long cartId, long productId);
}
