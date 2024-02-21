package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(long id, Product p) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }
}
