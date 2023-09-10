package com.akej24.chatAndChallengeApp.authentication.login;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class LoginMapper {

    static LoginQuery fromRequest(@Valid LoginRequest request) {
        return new LoginQuery(
                request.email(),
                request.password()
        );
    }

    static LoginResponse toResponse(@Valid LoginResult result) {
        return new LoginResponse(
                result.jwt()
        );
    }
}
