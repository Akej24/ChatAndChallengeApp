package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
final class LoginQuery extends SelfValidating<LoginQuery> {

    @NotBlank(message = "Requested e-mail cannot be blank")
    @Email(message = "Requested e-mail has invalid format")
    private final String email;

    @NotBlank(message = "Requested password cannot be blank")
    private final String password;

    private LoginQuery(String email, String password) {
        this.email = email;
        this.password = password;
        validateSelf();
    }

    static LoginQuery from(String email, String password) {
        return new LoginQuery(email, password);
    }
}
