package com.cols.bank.transactions.security;

import com.google.gson.Gson;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cols.bank.transactions.security.TokenJwtConfig.SECRET_KEY;

public class JwtUtil {

    private static final Gson gson = new Gson();

    public static String validateToken(String token) {
        Map<String, Object> body = new HashMap<>();
        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            if (!claims.isEmpty()) {
                String username = claims.getSubject();
                String newToken = Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 3600000))
                        .issuedAt(new Date())
                        .signWith(SECRET_KEY)
                        .compact();

                Map<String, Object> userMap = new HashMap<>();
                userMap.put("username", username);
                body.put("user", userMap);
                body.put("token", newToken);
                body.put("statusCode", HttpStatus.OK.value());
            }
        } catch (JwtException | IllegalArgumentException e) {
            body.put("message", "Error en la autenticación, username o password incorrectos.");
            body.put("error", e.getMessage());
            body.put("statusCode", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }
        return gson.toJson(body);
    }
}
