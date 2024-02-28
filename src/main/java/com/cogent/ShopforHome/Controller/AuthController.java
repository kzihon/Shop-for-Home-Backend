package com.cogent.ShopforHome.Controller;

import com.cogent.ShopforHome.entity.User;
import com.cogent.ShopforHome.entity.dto.request.LoginRequest;
import com.cogent.ShopforHome.entity.dto.request.RefreshTokenRequest;
import com.cogent.ShopforHome.entity.dto.request.SignupRequest;
import com.cogent.ShopforHome.entity.dto.response.LoginResponse;
import com.cogent.ShopforHome.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        User user = null;
        try {
            user = authService.signup(signupRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

}