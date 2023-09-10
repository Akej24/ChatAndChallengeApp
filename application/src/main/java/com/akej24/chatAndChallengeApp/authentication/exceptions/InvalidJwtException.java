package com.akej24.chatAndChallengeApp.authentication.exceptions;

public class InvalidJwtException extends GenericAuthenticationException{

    public InvalidJwtException() {
        super("Could not create or verify jwt");
    }
}
