package com.shoplify.shoplify.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shoplify.shoplify.models.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String key;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    private static final String USERNAME = "username";

    private static final String EMAIL = "email";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(key);

    }

    public String getToken(LocalUser user) {
        return JWT.create().withClaim(USERNAME, user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer).sign(algorithm);
    }

    public String generateVerificationJWT(LocalUser user){
        return JWT.create().withClaim(EMAIL, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer).sign(algorithm);
    }

    public String getUsername(String token) {
        return JWT.decode(token).getClaim(USERNAME).asString();
    }
}
