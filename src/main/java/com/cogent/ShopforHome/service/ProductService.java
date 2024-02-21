package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Product;

import java.util.List;

public interface CustomerService {
    public Product createProduct(Product p);
    public Product updateProduct(long id, Product p);
    public List<Product> getProducts();
    public void deleteProduct(long id);
}
