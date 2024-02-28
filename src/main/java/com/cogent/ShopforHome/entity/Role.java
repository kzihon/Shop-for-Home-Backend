package com.cogent.ShopforHome.entity;

import com.cogent.ShopforHome.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private RoleType role;


    public Role(RoleType role) {
        this.role = role;
    }
}
