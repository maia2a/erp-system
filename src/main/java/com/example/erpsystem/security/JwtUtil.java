package com.example.erpsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "password"; // Replace with a secure secret key
  private static final long EXPIRATION_TIME = 864_000_000; // 10 days

  // Generates a JWT token
  public String generateToken(String username) {
    SecretKey key = getSigningKey();
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key)
        .compact();
  }

  // Extracts all claims from the token
  public Claims extractAllClaims(String token) {
    SecretKey key = getSigningKey();
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  // Validates the token
  public boolean validateToken(String token, String username) {
    final String extractedUsername = extractAllClaims(token).getSubject();
    return (extractedUsername.equals(username) && !isTokenExpired(token));
  }

  // Checks if the token is expired
  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  // Helper method to decode the secret key
  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}