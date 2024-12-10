package com.shoplify.shoplify.api.controller.auth;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.LoginResponse;
import com.shoplify.shoplify.api.model.PasswordReset;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.exception.EmailNotFoundException;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.exception.UserNotVerified;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.service.UserSerivce;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

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
        } catch (EmailFailureException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token){
    if(userSerivce.verifyUser(token)){
        return ResponseEntity.ok().build();
    }else {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody Login login) {
        String jwt = null;
        try{
            jwt = userSerivce.logIn(login);
        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (UserNotVerified e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            String reason = "USER_NOT_VERIFIED";
            if(e.isNewEmailSent()){
                reason += "_EMAIL_RESENT";
            }
            loginResponse.setFailureReason(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);
        }
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        }

    }

    @GetMapping("/me")
    public LocalUser  getCurrentUser(@AuthenticationPrincipal LocalUser user){
        return user;
    }

    @PostMapping("/forgot")
    public ResponseEntity forgotPassword(@RequestParam String email){
        try{
            userSerivce.forgotPassword(email);
            return ResponseEntity.ok().build();
        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch(EmailNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@Valid @RequestParam PasswordReset passwordReset){
        userSerivce.resetPassword(passwordReset);
        return ResponseEntity.ok().build();
    }
}
