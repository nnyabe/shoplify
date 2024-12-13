package com.shoplify.shoplify.api.interfaces.controllers;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.LoginResponse;
import com.shoplify.shoplify.api.model.PasswordReset;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.exception.EmailNotFoundException;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.exception.UserNotVerified;
import org.springframework.http.ResponseEntity;

public interface AuthenticationInterface {

        ResponseEntity<Registration> registerUser(Registration registration) throws UserAlreadyExistsException, EmailFailureException;

    ResponseEntity<String> verifyEmail(String token);

    ResponseEntity<LoginResponse> loginUser(Login login) throws EmailFailureException, UserNotVerified;

    ResponseEntity<String> forgotPassword(String email) throws EmailFailureException, EmailNotFoundException;

    ResponseEntity<String> resetPassword(PasswordReset passwordReset);

}
