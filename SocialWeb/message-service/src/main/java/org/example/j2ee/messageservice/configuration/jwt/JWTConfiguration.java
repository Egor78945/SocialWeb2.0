package org.example.j2ee.messageservice.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JWTConfiguration {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime}")
    private int lifetime;

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", getRolesFromPrincipal(authentication));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(getUserDetails(authentication).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + lifetime))
                .signWith(getSigningKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSigningKey(String secret) {
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoleFromToken(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey(secret))
                .build().parseClaimsJws(token)
                .getBody();
    }

    private List<String> getRolesFromPrincipal(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private UserDetails getUserDetails(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }
}
