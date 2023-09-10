package com.akej24.chatAndChallengeApp.authentication.login.value_objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class EncodedPassword implements Serializable {

    @NotBlank(message="Password must not be blank")
    private String encodedPassword;

}
