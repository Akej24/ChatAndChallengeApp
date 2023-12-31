package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.exceptions.InvalidJwtException;
import com.akej24.chatAndChallengeApp.authentication.exceptions.InvalidPasswordForGivenEmailException;
import com.akej24.chatAndChallengeApp.authentication.exceptions.LockedAccountException;
import com.akej24.chatAndChallengeApp.authentication.exceptions.UserEmailNotFoundException;
import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import com.akej24.chatAndChallengeApp.common.QueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
final class LoginQueryHandler implements QueryHandler<LoginQuery, LoginResult> {

    private final IAuthenticationTokenContext authenticationTokenContext;
    private final IPasswordValidator passwordValidator;
    private final IUserCredentialsRepository userCredentialsRepository;
    private final IJwtService jwtService;
    private final IJwtRepository jwtRepository;

    @Override
    public LoginResult handle(LoginQuery loginQuery) throws LockedAccountException {
        var userCredentials = getUserCredentials(loginQuery);
        log.info("User is trying to log in, userId: {}", userCredentials.getUserId().getUserId());

        if(!passwordValidator.isUserPasswordValid(loginQuery, userCredentials)) throw new InvalidPasswordForGivenEmailException();
        if(userCredentials.isAccountLocked()) throw new LockedAccountException();
        log.info("User credentials are valid, userId: {}", userCredentials.getUserId().getUserId());

        var jwt = generateJwtFromCredentials(userCredentials);
        log.info("Successfully generated jwt for user, userId: {}", userCredentials.getUserId().getUserId());

        jwtRepository.saveForUser(userCredentials.getUserId().getUserId(), jwt.jwt());
        authenticationTokenContext.authenticateToContext(loginQuery.getEmail(), loginQuery.getPassword());
        log.info("User has successfully logged in, userId: {}", userCredentials.getUserId().getUserId());

        return LoginResult.from(jwt.jwt());
    }

    private Jwt generateJwtFromCredentials(UserCredentials userCredentials) {
        return jwtService
                .generateJwt(userCredentials)
                .orElseThrow(InvalidJwtException::new);
    }

    private UserCredentials getUserCredentials(LoginQuery loginQuery) {
        return userCredentialsRepository
                .findByEmail_Email(loginQuery.getEmail())
                .orElseThrow(UserEmailNotFoundException::new);
    }
}
