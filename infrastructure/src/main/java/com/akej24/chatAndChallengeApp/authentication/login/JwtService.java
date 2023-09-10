package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JwtService implements IJwtService {

    @Value("${module.jwt.secretKey}")
    private String secretKey;

    @Override
    public Optional<Jwt> generateJwt(UserCredentials userCredentials) {
        try {
            String jwt = JWT.create()
                    .withSubject(userCredentials.getEmail().getEmail())
                    .withClaim("userId", userCredentials.getUserId().getUserId().toString())
                    .withClaim("role", userCredentials.getRole().name())
                    .sign(Algorithm.HMAC256(secretKey));
            return Optional.of(Jwt.from(jwt));
        } catch (JWTCreationException exception) {
            return Optional.empty();
        }
    }

    @Override
    public boolean validateJwt(UserCredentials userCredentials, Jwt jwt) {
        try {
            JWTVerifier verifier = JWT
                    .require(Algorithm.HMAC256(secretKey))
                    .withSubject(userCredentials.getEmail().getEmail())
                    .withClaim("userId", userCredentials.getUserId().getUserId().toString())
                    .withClaim("roles", userCredentials.getRole().name())
                    .build();
            verifier.verify(jwt.jwt());
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }
}
