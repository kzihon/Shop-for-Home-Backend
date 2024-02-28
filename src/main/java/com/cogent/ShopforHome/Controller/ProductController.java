package com.cogent.ShopforHome.Controller;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value="/product/get")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        if (products == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    @PostMapping(value="/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product p) {
        if (productService.createProduct(p) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
    @GetMapping(value="/product/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product p=productService.getProductById(id);
        if( p==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    @PutMapping(value="/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product p) {

        if (productService.updateProduct(id,p) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    @DeleteMapping(value="/product/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }
}
