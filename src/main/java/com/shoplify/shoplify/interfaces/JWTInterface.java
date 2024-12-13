package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.models.LocalUser;

public interface JWTInterface {
    String getToken(LocalUser user);

    String generateVerificationJWT(LocalUser user);

    String generatePasswordResetJWT(LocalUser user);

    String getResetPasswordEmail(String token);

    String getUsername(String token);
}
