package com.akej24.chatAndChallengeApp.authentication.login.value_objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class UserId implements Serializable {

    @NotNull(message = "User id cannot be null")
    private UUID userId;

}
