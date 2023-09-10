package com.akej24.chatAndChallengeApp.authentication.exceptions;

public class InvalidPasswordForGivenEmailException extends GenericAuthenticationException {

    public InvalidPasswordForGivenEmailException() {
        super("Invalid password for given e-mail");
    }
}
