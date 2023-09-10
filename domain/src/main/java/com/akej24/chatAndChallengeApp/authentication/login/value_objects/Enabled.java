package com.akej24.chatAndChallengeApp.authentication.login.value_objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class Enabled implements Serializable {

        @NotNull(message = "User enabled property cannot be null")
        private boolean enabled;

}
