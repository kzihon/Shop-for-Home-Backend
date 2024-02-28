package com.cogent.ShopforHome.entity;

import com.cogent.ShopforHome.entity.enums.RoleType;
import jakarta.persistence.*;
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    public Role(){

    }

    public Role(RoleType role) {
        this.role = role;
    }
}
