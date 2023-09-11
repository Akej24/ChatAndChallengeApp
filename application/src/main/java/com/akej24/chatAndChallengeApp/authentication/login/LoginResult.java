package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
final class LoginResult extends SelfValidating<LoginResult> {

    @NotBlank(message = "Resulted jwt should not be blank")
    private final String jwt;

    private LoginResult(String jwt) {
        this.jwt = jwt;
        validateSelf();
    }

    static LoginResult from(String jwt) {
        return new LoginResult(jwt);
    }
}
