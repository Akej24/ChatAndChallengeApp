package com.akej24.chatAndChallengeApp.authentication.login;

import java.util.UUID;

interface IJwtRepository {

    void saveForUser(UUID userId, String jwt);

    String getByUser(UUID userId);

    void deleteByUser(UUID userId);
}
