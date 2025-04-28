package com.example.erpsystem.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {

  @Autowired
  private JwtUtil jwtUtil;

  @Test
  void testGenerateAndValidateToken() {
    String username = "testUser";
    String token = jwtUtil.generateToken(username);

    assertNotNull(token, "Generated token should not be null");
    assertTrue(jwtUtil.validateToken(token, username), "Token validation should succeed");
  }
}