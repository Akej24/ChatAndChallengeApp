package com.akej24.chatAndChallengeApp.authentication.login;

import jakarta.validation.constraints.NotBlank;

record LoginResult (

        @NotBlank(message = "Jwt should not be blank")
        String jwt

) {
    static LoginResult from(String jwt) {
        return new LoginResult(jwt);
    }
}
