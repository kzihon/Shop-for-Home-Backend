package com.cogent.ShopforHome.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long productId;
    private String name;
    private double price;
    private String description;
    private String category;
    private int numberInStock;
    private String supplierName;
//    @ManyToMany(mappedBy = "productWishList")
//    @JsonBackReference
//    private List<Customer> customers;
    public Product(){}
    public Product(long productId, String name, double price, String description, String category, int numberInStock, String supplierName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.numberInStock = numberInStock;
        this.supplierName = supplierName;
    }

    public Product(String name, double price, String description, String category, int numberInStock, String supplierName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.numberInStock = numberInStock;
        this.supplierName = supplierName;
    }

//    public List<Customer> getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(List<Customer> customers) {
//        this.customers = customers;
//    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", numberInStock=" + numberInStock +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
