package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.api.model.Login;
import com.shoplify.shoplify.api.model.PasswordReset;
import com.shoplify.shoplify.api.model.Registration;
import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.exception.EmailNotFoundException;
import com.shoplify.shoplify.exception.UserAlreadyExistsException;
import com.shoplify.shoplify.exception.UserNotVerified;

public interface UserServiceInterface {
    void registerUser(Registration registration) throws UserAlreadyExistsException, EmailFailureException;

    String logIn(Login login) throws UserNotVerified, EmailFailureException;

    boolean verifyUser(String token);

    void forgotPassword(String email) throws EmailNotFoundException, EmailFailureException;

    void resetPassword(PasswordReset body);
}
