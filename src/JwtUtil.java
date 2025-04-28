package com.example.erpsystem.security;

import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "password"; // Replace with your secret key
  private static final long EXPIRATION_TIME = 864_000_000; // 10 days

  // Generates a JWT token
  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  // Validates the token
  public Claims extractAlClaims(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }

  // Extracts the username from the token
  public boolean validateToken(String token, String username) {
    final String extractedUsername = extractAlClaims(token).getSubject();
    return (extractedUsername.equals(username) && !isTokenExpired(token));
  }

  // Extracts the expiration date from the token
  private boolean isTokenExpired(String token) {
    return extractAlClaims(token).getExpiration().before(new Date());
  }
}
