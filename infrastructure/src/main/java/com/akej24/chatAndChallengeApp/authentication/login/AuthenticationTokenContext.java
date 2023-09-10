package com.akej24.chatAndChallengeApp.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AuthenticationTokenContext implements IAuthenticationTokenContext {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticateToContext(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
