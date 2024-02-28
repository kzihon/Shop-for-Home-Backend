package com.cogent.ShopforHome.Controller;

import com.cogent.ShopforHome.entity.Customer;
import com.cogent.ShopforHome.entity.Order;
import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.entity.ShoppingCart;
import com.cogent.ShopforHome.service.CustomerService;
import com.cogent.ShopforHome.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @PostMapping(value="/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
        if (customerService.createCustomer(c) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    @GetMapping(value="/get")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer c=customerService.getCustomerById(id);
        if( c==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
    @DeleteMapping(value="/delete/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
    }
    @GetMapping("/{customer_id}/favorites/{product_id}")
    public ResponseEntity<String> addToFavorites(@PathVariable long customer_id, @PathVariable long product_id){

        try {
            customerService.addToFavorites(customer_id, product_id);
            return ResponseEntity.ok("Product added to wishlist successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{customer_id}/favorites")
    public List<Product> getFavorites(@PathVariable long customer_id) {
        List<Product> products = customerService.getFavorites(customer_id);

        return products;
    }


    @DeleteMapping("/{customer_id}/favorites/{property_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteFromFavorites(@PathVariable long customer_id, @PathVariable long property_id) {

        try {
            customerService.removeFromFavorites(customer_id, property_id);
            return ResponseEntity.ok("Product removed from wishlist successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




//    public void addProductToCart(long customerId, long productId);
//    public void removeProductFromCart(long customerId, long productId);
//    public void increaseQuntityOfProductInCart(long customerId,  long productId,int quantity);
//    public ShoppingCart getCart(long customerId);


//    @DeleteMapping(value="/customer/{customer_id}/shoppingcart/{cartId}/removeProduct/{product_id}")
//    public void removeProductFromCart(@PathVariable long customer_id,@PathVariable long cartId, @PathVariable long product_id){
//        customerService.removeProductFromCart(customer_id, cartId, product_id);
//    }
//    @GetMapping("customer/{customer_id}/shoppingcart/{cartId}/addProduct/{product_id}")
//    public void addProductToCart(@PathVariable long customer_id,@PathVariable long cartId, @PathVariable long product_id){
//
//        customerService.addProductToCart(customer_id,cartId, product_id);
//    }
//    @GetMapping("customer/{customer_id}/shoppingcart/{cartId}/product/{product_id}/quantity/{quantity}")
//    public void increaseQuntityOfProductInCart(@PathVariable long customer_id,@PathVariable long cartId, @PathVariable long product_id, @PathVariable int quantity){
//
//        customerService.increaseQuntityOfProductInCart(customer_id,cartId, product_id,quantity);
//    }

//    @GetMapping("customer/{customer_id}/shopping-cart")
//    public ResponseEntity<String> updateCartAndStock(@PathVariable long customer_id, @RequestBody ShoppingCart sc){
//
//        try {
//            customerService.proceedToCheckout(customer_id, sc);
//            return ResponseEntity.ok("Shopping Cart and Stock updated successfully");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//       // customerService.increaseQuntityOfProductInCart(customer_id,cartId, product_id,quantity);
//    }
    @GetMapping("/{customer_id}/shopping-cart")
    public ResponseEntity<Order> proceedToCheckOutandGetOrder(@PathVariable long customer_id, @RequestBody ShoppingCart sc){

        try {
            Order order=customerService.proceedToCheckout(customer_id, sc);
             return ResponseEntity.status(HttpStatus.OK).body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{customer_id}")
    public ShoppingCart getCart(@PathVariable long customer_id){

       return customerService.getCart(customer_id);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> filterProductByCategory(@PathVariable String category) {

            List<Product> products= customerService.filterProductByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(products);

    }
}
