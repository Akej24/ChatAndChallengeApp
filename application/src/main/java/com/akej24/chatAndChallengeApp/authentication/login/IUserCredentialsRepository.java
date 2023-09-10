package com.akej24.chatAndChallengeApp.authentication.login;

import java.util.Optional;

interface IUserCredentialsRepository {

    Optional<UserCredentials> findByEmail_Email(String email);

}
