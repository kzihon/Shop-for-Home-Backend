package com.cogent.ShopforHome.repository;

import com.cogent.ShopforHome.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
