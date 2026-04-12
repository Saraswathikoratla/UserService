package com.saraswathi.util;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final String SECRET = "my-super-secret-key-my-super-secret-key";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ✅ GENERATE TOKEN
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(getKey(), SignatureAlgorithm.HS256)   // ✅ FIX
                .compact();
    }

    // ✅ EXTRACT USERNAME
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())   // ✅ FIX
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}