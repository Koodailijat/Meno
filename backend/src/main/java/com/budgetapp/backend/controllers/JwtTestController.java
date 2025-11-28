package com.budgetapp.backend.controllers;

import com.budgetapp.backend.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTestController {

    private final JwtUtil jwtUtil;

    public JwtTestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/jwttest")
    public Object jwtTest(@RequestParam(required = false) String token) {
        if (token == null) {
            return "Token missing. Provide ?token=YOUR_JWT";
        }
        return jwtUtil.extractAllClaims(token);
    }

}
