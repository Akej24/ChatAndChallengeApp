package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.common.AuthenticationFacade;
import com.akej24.chatAndChallengeApp.authentication.exceptions.InvalidJwtException;
import com.akej24.chatAndChallengeApp.authentication.exceptions.UsernameNotFoundException;
import com.akej24.chatAndChallengeApp.authentication.login.value_objects.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class LoginFilterConfig extends OncePerRequestFilter {

    private final AuthenticationFacade authenticationFacade;
    private final UserCredentialsRepository userCredentialsRepository;
    private final JwtRepository jwtRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (!validateAuthHeader(request, response, filterChain, authHeader)) return;

        Jwt jwtFromHeader = Jwt.from(authHeader.substring(7));
        String username = authenticationFacade.getUserEmailFromJwt(jwtFromHeader);
        if(checkUsernameExistsInSecurityContext(request, response, filterChain, username)) return;

        var userDetails = this.userDetailsService.loadUserByUsername(username);
        var userCredentials = getUserCredentials(username);
        var userId = userCredentials.getUserId().getUserId();
        if(!checkJwtExistsInRepository(request, response, filterChain, userId)) return;

        if(!jwtService.validateJwt(userCredentials, jwtFromHeader)) throw new InvalidJwtException();
        setAuthTokenToSecurityContext(request, userDetails);
        filterChain.doFilter(request, response);
    }

    private boolean validateAuthHeader(HttpServletRequest request,
                                       HttpServletResponse response,
                                       FilterChain filterChain,
                                       String authHeader
    ) throws ServletException, IOException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return false;
        }
        return true;
    }

    private boolean checkUsernameExistsInSecurityContext(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         FilterChain filterChain,
                                                         String username
    ) throws ServletException, IOException {
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            filterChain.doFilter(request, response);
            return false;
        }
        return true;
    }

    private boolean checkJwtExistsInRepository(HttpServletRequest request,
                                               HttpServletResponse response,
                                               FilterChain filterChain,
                                               UUID userId
    ) throws ServletException, IOException {
        if(jwtRepository.getByUser(userId) == null){
            filterChain.doFilter(request, response);
            return false;
        }
        return true;
    }

    private static void setAuthTokenToSecurityContext(HttpServletRequest request, UserDetails userDetails) {
        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private UserCredentials getUserCredentials(String username) {
        return userCredentialsRepository
                .findByEmail_Email(username)
                .orElseThrow(UsernameNotFoundException::new);
    }
}
