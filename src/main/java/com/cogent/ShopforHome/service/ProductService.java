package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product p);
    public Product updateProduct(long id, Product p);
    public List<Product> getProducts();
    public Product getProductById(long id);
    public void deleteProduct(long id);
}
