package com.cogent.ShopforHome.repository;

import com.cogent.ShopforHome.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
