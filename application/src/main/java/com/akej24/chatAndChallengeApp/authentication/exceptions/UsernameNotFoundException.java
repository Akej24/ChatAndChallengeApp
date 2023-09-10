package com.akej24.chatAndChallengeApp.authentication.exceptions;

public class UsernameNotFoundException extends GenericAuthenticationException {

    public UsernameNotFoundException() {
        super("Username has not been found");
    }

}
