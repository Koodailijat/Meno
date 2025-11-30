package com.budgetapp.backend.security;

import com.budgetapp.backend.model.User;
import com.budgetapp.backend.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil, UserRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String googleId = oidcUser.getSubject();

        User user = userRepo.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("User not found after login"));

        String token = jwtUtil.generateToken(user);

        // write token in cookie
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setHttpOnly(false); // frontend JS wants to read it
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        // redirect to frontend
        // Read the environment variable
        String redirectUrl = System.getenv("LOGIN_REDIRECT");

// Fallback if environment variable is not set
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            redirectUrl = "http://localhost:8080/jwttest"; // default value
        }

// Append the token
        redirectUrl += "?token=" + token;

// Perform the redirect
        response.sendRedirect(redirectUrl);
    }
}
