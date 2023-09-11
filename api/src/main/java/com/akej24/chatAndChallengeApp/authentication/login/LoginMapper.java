package com.akej24.chatAndChallengeApp.authentication.login;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class LoginMapper {

    static LoginQuery fromRequest(LoginRequest request) {
        return LoginQuery.from(
                request.email(),
                request.password()
        );
    }

    static LoginResponse toResponse(LoginResult result) {
        return new LoginResponse(result.getJwt());
    }
}
