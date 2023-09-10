package com.akej24.chatAndChallengeApp.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class LoginQueryHandlerConfig {

    private final AuthenticationTokenContext authenticationTokenContext;
    private final PasswordValidator passwordValidator;
    private final UserCredentialsRepository userCredentialsRepository;
    private final JwtService jwtService;
    private final JwtRepository jwtRepository;

    @Bean
    LoginQueryHandler loginQueryHandler() {
        return new LoginQueryHandler(
                authenticationTokenContext,
                passwordValidator,
                userCredentialsRepository,
                jwtService,
                jwtRepository
        );
    }
}
