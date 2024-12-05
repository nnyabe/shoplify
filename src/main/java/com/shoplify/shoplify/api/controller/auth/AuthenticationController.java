package com.shoplify.shoplify.api.controller.auth;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.LoginResponse;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.service.UserSerivce;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {
    private UserSerivce userSerivce;

    public AuthenticationController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody Registration registration) {
        try{
            userSerivce.registerUser(registration);
            return ResponseEntity.ok().build();
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody Login login) {
        String jwt = userSerivce.logIn(login);
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            return ResponseEntity.ok().body(response);
        }

    }

    @GetMapping("/me")
    public LocalUser  getCurrentUser(@AuthenticationPrincipal LocalUser user){
        return user;
    }

}
