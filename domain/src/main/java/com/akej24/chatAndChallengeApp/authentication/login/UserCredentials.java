package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
class UserCredentials implements Serializable {

    @Valid @EmbeddedId
    private UserId userId;

    @Valid @Embedded
    private UserEmail email;

    @Valid @Embedded
    private EncodedPassword encodedPassword;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must not be null")
    private UserRole role;

    @Valid @Embedded
    private Locked locked;

    boolean isPasswordEqualTo(EncodedPassword givenPassword){
        return this.encodedPassword.equals(givenPassword);
    }

    boolean isAccountLocked() {
        return this.locked.isLocked();
    }

}
