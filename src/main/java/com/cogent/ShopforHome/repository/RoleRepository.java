package com.cogent.ShopforHome.repository;

import com.cogent.ShopforHome.entity.Role;
import com.cogent.ShopforHome.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleType role);
}
