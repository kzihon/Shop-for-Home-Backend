package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.User;
import com.cogent.ShopforHome.entity.dto.request.LoginRequest;
import com.cogent.ShopforHome.entity.dto.request.RefreshTokenRequest;
import com.cogent.ShopforHome.entity.dto.request.SignupRequest;
import com.cogent.ShopforHome.entity.dto.response.LoginResponse;

import java.io.IOException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    User signup(SignupRequest signupRequest) throws IOException;
}
