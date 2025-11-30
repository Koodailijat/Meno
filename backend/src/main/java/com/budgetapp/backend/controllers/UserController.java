package com.budgetapp.backend.controllers;

import com.budgetapp.backend.model.CustomOAuth2User;
import com.budgetapp.backend.model.User;
import com.budgetapp.backend.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/user/me")
    public User me(@AuthenticationPrincipal CustomOAuth2User principal) {
        return principal != null ? principal.getUser() : null;
    }




}
