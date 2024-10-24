package com.api.jwt.security;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
//	private static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode("S0VZX1BBUkFfRUpFTVBMT19NVUxFU09GVA=="),
//			SignatureAlgorithm.HS512.getJcaName());
	
	private static final Key hmacKey = generateKey();

    private static Key generateKey() {
        byte[] keyBytes = new byte[32]; // 32 bytes = 256 bits
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(hmacKey)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
    	return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody().getExpiration();
    }

    public String extractUsername(String token) {
    	return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody().getSubject();
    }
}
