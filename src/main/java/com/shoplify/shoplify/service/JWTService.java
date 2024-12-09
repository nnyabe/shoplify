package com.shoplify.shoplify.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shoplify.shoplify.models.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

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

    public String getToken(LocalUser user) {
        return JWT.create().withClaim(USERNAME, user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiryInSeconds)))
                .withIssuer(issuer).sign(algorithm);
    }

    public String generateVerificationJWT(LocalUser user){
        return JWT.create().withClaim(EMAIL, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiryInSeconds)))
                .withIssuer(issuer).sign(algorithm);
    }

    public String generatePasswordResetJWT(LocalUser user){
        return JWT.create().withClaim(EMAIL, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * passwordResetExpiry)))
                .withIssuer(issuer).sign(algorithm);
    }

    public String getResetPasswordEmail(String token){
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);

        return JWT.decode(token).getClaim(USERNAME).asString();
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);

        return JWT.decode(token).getClaim(USERNAME).asString();
    }
}
