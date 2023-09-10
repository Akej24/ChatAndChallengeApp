package com.akej24.chatAndChallengeApp.authentication.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

record LoginRequest(

        @NotBlank(message = "Requested e-mail cannot be blank")
        @Email(message = "Requested e-mail has invalid format")
        String email,

        @NotBlank(message = "Requested password cannot be blank")
        String password

) { }
