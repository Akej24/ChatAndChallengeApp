package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.exceptions.UserEmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
class AuthenticationConfig {

    private final IUserCredentialsRepository userCredentialsRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> buildUserDetailsFromCredentials(
                userCredentialsRepository
                        .findByEmail_Email(email)
                        .orElseThrow(UserEmailNotFoundException::new)
        );
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private UserDetails buildUserDetailsFromCredentials(UserCredentials userCredentials) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton((GrantedAuthority) () -> userCredentials.getRole().name());
            }

            @Override
            public String getPassword() {
                return userCredentials.getEncodedPassword().getEncodedPassword();
            }

            @Override
            public String getUsername() {
                return userCredentials.getEmail().getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return userCredentials.getLocked().isLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return userCredentials.getEnabled().isEnabled();
            }
        };
    }
}
