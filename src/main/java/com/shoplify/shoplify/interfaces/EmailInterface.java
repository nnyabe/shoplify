package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.exception.EmailFailureException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.VerificationToken;

public interface EmailInterface {
    void sendVerificationEmail(VerificationToken verificationToken) throws EmailFailureException;

    void sendPasswrodResetEmail(LocalUser user, String token) throws EmailFailureException;
}
