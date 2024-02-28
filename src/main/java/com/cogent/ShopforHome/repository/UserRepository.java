package com.cogent.ShopforHome.repository;

import com.cogent.ShopforHome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
