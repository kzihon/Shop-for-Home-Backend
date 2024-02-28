package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Customer;
import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.entity.ShoppingCart;
import com.cogent.ShopforHome.repository.ProductRepository;
import com.cogent.ShopforHome.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ShoppingCart createCart(ShoppingCart sc) {
        return cartRepository.save(sc);
    }

    @Override
    public ShoppingCart updateCart(long id, ShoppingCart p) {
        return null;
    }

    @Override
    public List<ShoppingCart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public ShoppingCart getCartById(long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public void deleteCart(long id) {
        cartRepository.deleteById(id);

    }


//    @Override
//    public void addProductToCart(long cartId, long productId) {
//        ShoppingCart cart=cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("customer not found"));
//        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//        cart.getProducts().add(product);
//        product.setNumberInStock(product.getNumberInStock()-1);
//        cartRepository.save(cart);
//        productRepository.save(product);
//
//    }
//
//    @Override
//    public void removeProductFromCart(long cartId, long productId) {
//        ShoppingCart cart=cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("customer not found"));
//        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//        if(!cart.getProducts().contains(product)) {
//            throw new RuntimeException("Property not found");
//        }
//        cart.getProducts().remove(product);
//        product.setNumberInStock(product.getNumberInStock()+1);
//
//        cartRepository.save(cart);
//        productRepository.save(product);
//    }
//
//    @Override
//    public void increaseQuntityOfProductInCart(long cartId, long productId, int quantity) {
//        ShoppingCart cart=cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("customer not found"));
//        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//       if(product.getNumberInStock()<quantity){
//           throw new RuntimeException("product out of stock!");
//       }else if(quantity>1){
//            for(int i=0; i<quantity; i++){
//                cart.getProducts().add(product);
//            }
//           product.setNumberInStock(product.getNumberInStock()-quantity);
//           cartRepository.save(cart);
//           productRepository.save(product);
//
//       }
//
//
//    }

//    @Override
//    public void decreaseQuntityOfProductInCart(long cartId, long productId, int quantity) {
//
//    }
}
