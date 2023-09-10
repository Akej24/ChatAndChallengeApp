package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.EncodedPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PasswordValidator implements IPasswordValidator {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isUserPasswordValid(LoginQuery loginQuery, UserCredentials userCredentials) {
        var encodedPassword = new EncodedPassword(passwordEncoder.encode(loginQuery.password()));
        return userCredentials.isPasswordEqualTo(encodedPassword);
    }
}
