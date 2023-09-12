package com.akej24.chatAndChallengeApp.authentication.exceptions;

public class LockedAccountException extends GenericAuthenticationException {

    public LockedAccountException() {
        super("User account is locked");
    }
}
