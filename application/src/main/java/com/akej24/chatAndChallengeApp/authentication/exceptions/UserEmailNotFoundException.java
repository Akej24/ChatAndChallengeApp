package com.akej24.chatAndChallengeApp.authentication.exceptions;

public class UserEmailNotFoundException extends GenericAuthenticationException {

    public UserEmailNotFoundException() {
        super("User with given e-mail has not been found");
    }
}
