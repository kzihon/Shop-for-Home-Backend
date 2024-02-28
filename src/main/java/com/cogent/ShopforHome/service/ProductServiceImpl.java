package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(long id, Product p) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            if(p.getName()!=null){
                product.setName(p.getName());
            }
            if(p.getPrice() != 0.0){
                product.setPrice(p.getPrice());
            }
            if(p.getDescription()!=null){
                product.setDescription(p.getDescription());
            }
            if(p.getCategory()!=null){
                product.setCategory(p.getCategory());
            }
            if(p.getName()!=null){
                product.setName(p.getName());
            }
            if(p.getNumberInStock()!=0){
                product.setNumberInStock(p.getNumberInStock());
            }
            if(p.getSupplierName()!=null){
                product.setSupplierName(p.getSupplierName());
            }
            return productRepository.save(product);

        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public void deleteProduct(long id) {

        productRepository.deleteById(id);
    }
}
