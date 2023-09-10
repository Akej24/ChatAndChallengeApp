package com.akej24.chatAndChallengeApp.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
class JwtRepository implements IJwtRepository {

    private static final String PREFIX = "user:";
    private final RedisTemplate<String, String> template;

    @Override
    public void saveForUser(UUID userId, String jwt) {
        template.opsForValue().set(PREFIX + userId, jwt);
        template.boundValueOps(PREFIX + userId).expire(7, TimeUnit.DAYS);
    }

    @Override
    public String getByUser(UUID userId) {
        return template.opsForValue().get(PREFIX + userId);
    }

    @Override
    public void deleteByUser(UUID userId) {
        template.delete(PREFIX + userId);
    }
}
