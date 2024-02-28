package com.cogent.ShopforHome.entity.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
