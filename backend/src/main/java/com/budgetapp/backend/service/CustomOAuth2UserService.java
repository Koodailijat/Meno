package com.budgetapp.backend.service;

import com.budgetapp.backend.model.CustomOAuth2User;
import com.budgetapp.backend.model.User;
import com.budgetapp.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class CustomOAuth2UserService extends OidcUserService {

    private final UserRepository repo;

    public CustomOAuth2UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest request) {
        OidcUser oidcUser = super.loadUser(request);

        String googleId = oidcUser.getSubject();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String picture = oidcUser.getUserInfo().getPicture();

        User user = repo.findByGoogleId(googleId).orElseGet(() ->
                repo.save(User.builder()
                        .googleId(googleId)
                        .email(email)
                        .name(name)
                        .avatarUrl(picture)
                        .lastLogin(Instant.now())
                        .build())
        );

        user.setLastLogin(Instant.now());
        repo.save(user);

        return new CustomOAuth2User(user, oidcUser);
    }
}

