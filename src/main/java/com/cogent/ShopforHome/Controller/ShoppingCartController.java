package com.cogent.ShopforHome.Controller;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.entity.ShoppingCart;
import com.cogent.ShopforHome.service.ProductService;
import com.cogent.ShopforHome.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;
    @GetMapping(value="/shoppingCart/get")
    public ResponseEntity<List<ShoppingCart>> getShoppingCart() {
        List<ShoppingCart> carts = cartService.getCarts();
        if (carts == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }
    @PostMapping(value="/shoppingCart/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart sc) {
        if (cartService.createCart(sc) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sc);
    }
    @GetMapping(value="/shoppingCart/get/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable long id) {
        ShoppingCart sc=cartService.getCartById(id);
        if( sc==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.OK).body(sc);
    }
//    @PutMapping(value="/shoppingCart/update/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product p) {
//
//        if (cartService.updateProduct(id,p) == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(p);
//    }
    @DeleteMapping(value="/shoppingCart/delete/{id}")
    public void deleteShoppingCart(@PathVariable long id){
        cartService.deleteCart(id);
    }
}

