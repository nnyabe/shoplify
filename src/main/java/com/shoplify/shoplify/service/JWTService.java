package com.shoplify.shoplify.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shoplify.shoplify.interfaces.JWTInterface;
import com.shoplify.shoplify.models.LocalUser;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService implements JWTInterface {

    @Value("${jwt.algorithm.key}")
    private String key;

    @Value("${passwordResetExpiry}")
    private int passwordResetExpiry;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    private static final String USERNAME = "username";

    private static final String EMAIL = "email";

    private static final String PASSWORD = "password";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(key);

    }
    private String generateToken(String claim, int expiry, LocalUser user) {
        return JWT.create().withClaim(claim, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiry)))
                .withIssuer(issuer).sign(algorithm);
    }

    private String verifyAndDecode(String token) {
        JWT.require(algorithm).withIssuer(issuer).build().verify(token);

        return JWT.decode(token).getClaim(USERNAME).asString();
    }

    @Override
    public String getToken(LocalUser user) {
        return generateToken(USERNAME, expiryInSeconds, user);
    }

    @Override
    public String generateVerificationJWT(LocalUser user){
        return generateToken(EMAIL, expiryInSeconds, user);
    }

    @Override
    public String generatePasswordResetJWT(LocalUser user){
        return generateToken(EMAIL, passwordResetExpiry, user);
    }

    @Override
    public String getResetPasswordEmail(String token){
       return verifyAndDecode(token);
    }

    @Override
    public String getUsername(String token) {
       return verifyAndDecode(token);
    }
}
