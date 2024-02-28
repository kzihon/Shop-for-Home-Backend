package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Customer;
import com.cogent.ShopforHome.entity.Order;
import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    public void addToFavorites(long customer_id, long property_id);
    public List<Product> getFavorites(long customer_id);
    public void removeFromFavorites(long customer_id, long property_id);
    public Customer createCustomer(Customer customer);
    public List<Customer> findAllCustomers();
    public Customer getCustomerById(long id);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(long id);


   // public void addProductToCart(long customerId, long cartId,long productId);
    //public void removeProductFromCart(long customerId,long cartId, long productId);
   // public void increaseQuntityOfProductInCart(long customerId, long cartId, long productId,int quantity);
    public ShoppingCart getCart(long customerId);
    public List<Product> filterProductByCategory(String category);



    public Order proceedToCheckout(long customerId, ShoppingCart shoppingCart);

}
