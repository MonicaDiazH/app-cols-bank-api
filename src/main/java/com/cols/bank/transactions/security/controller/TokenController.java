package com.cols.bank.transactions.security.controller;

import com.cols.bank.transactions.security.JwtUtil;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TokenController {

    private static final Gson gson = new Gson();

    @GetMapping(path = "/validate-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String validateToken(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> resp = new HashMap<>();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return JwtUtil.validateToken(token);
        }
        resp.put("message", "No se encontró token.");
        resp.put("error", "Token inválido.");
        resp.put("statusCode", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        return gson.toJson(resp);
    }
}
