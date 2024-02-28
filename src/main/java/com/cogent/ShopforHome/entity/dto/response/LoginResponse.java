package com.cogent.ShopforHome.entity.dto.response;

import com.cogent.ShopforHome.entity.enums.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String firstName;
    private long id;
    private RoleType role;


    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public LoginResponse(String accessToken, String refreshToken, String firstName, long userId, RoleType role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firstName = firstName;
        this.id = userId;
        this.role = role;

    }
}
