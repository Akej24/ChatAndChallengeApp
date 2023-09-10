package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import jakarta.validation.Valid;

import java.util.Optional;

interface IJwtService {

    Optional<Jwt> generateJwt(@Valid UserCredentials userCredentials);

    boolean validateJwt(@Valid UserCredentials userCredentials, Jwt jwt);
}
