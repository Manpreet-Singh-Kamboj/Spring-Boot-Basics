package com.blogapp.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public interface JWTService {
    String extractUserName(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    Claims extractAllClaims(String token);

    Boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    Key getSigningKey();
    String getEmailFromToken(String token);

    Boolean validateToken(String token);

    String generateRefresh(Map<String, Objects> extraClaims, UserDetails userDetails);

    String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails);
}
