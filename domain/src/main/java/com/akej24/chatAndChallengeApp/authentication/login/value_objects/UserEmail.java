package com.akej24.chatAndChallengeApp.authentication.login.value_objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class UserEmail implements Serializable {

        @NotBlank(message = "User e-mail cannot be blank")
        @Email(message = "Invalid e-mail format")
        private String email;

}
