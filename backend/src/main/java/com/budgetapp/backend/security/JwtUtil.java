package com.budgetapp.backend.security;

import com.budgetapp.backend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "S0meExtremelyStrongPrivateJwtSecretKey12345";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())     // ID
                .claim("email", user.getEmail())         // email
                .claim("name", user.getName())           // name
                .claim("googleId", user.getGoogleId())   // googleId
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1h
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
