package com.akej24.chatAndChallengeApp.authentication.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/login")
@RequiredArgsConstructor
class LoginController {

    private final LoginQueryHandler loginQueryHandler;

    @PostMapping
    ResponseEntity<Void> loginUser(@RequestBody LoginRequest request) {
        LoginQuery loginQuery = LoginMapper.fromRequest(request);
        LoginResult result = loginQueryHandler.handle(loginQuery);
        LoginResponse response = LoginMapper.toResponse(result);
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + response.jwt());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}
