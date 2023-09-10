package com.akej24.chatAndChallengeApp.authentication.login.common;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;

public interface IAuthenticationFacade {

    String getUserEmailFromJwt(Jwt jwt);

    String getUserIdFromJwt(Jwt jwt);
}
