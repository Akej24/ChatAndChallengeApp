package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.common.AuthenticationFacade;
import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
class LogoutConfig  {

    private final JwtRepository jwtRepository;
    private final AuthenticationFacade authenticationFacade;

    @Bean
    LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null ||!authHeader.startsWith("Bearer ")) return;
            Jwt jwtFromHeader = Jwt.from(authHeader.substring(7));
            String userId = authenticationFacade.getUserIdFromJwt(jwtFromHeader);
            jwtRepository.deleteByUser(UUID.fromString(userId));
            SecurityContextHolder.clearContext();
        };
    }
}
