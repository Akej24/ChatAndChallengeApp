package com.akej24.chatAndChallengeApp.authentication.login;

interface IPasswordValidator {

    boolean isUserPasswordValid(LoginQuery loginQuery, UserCredentials userCredentials);
}
