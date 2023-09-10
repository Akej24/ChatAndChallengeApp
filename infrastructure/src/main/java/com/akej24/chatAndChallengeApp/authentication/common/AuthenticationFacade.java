package com.akej24.chatAndChallengeApp.authentication.common;

import com.akej24.chatAndChallengeApp.authentication.login.common.IAuthenticationFacade;
import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public String getUserEmailFromJwt(Jwt jwt) {
        return JWT.decode(jwt.jwt())
                .getSubject();
    }

    @Override
    public String getUserIdFromJwt(Jwt jwt) {
        return JWT.decode(jwt.jwt())
                .getClaim("userId")
                .asString();
    }
}
