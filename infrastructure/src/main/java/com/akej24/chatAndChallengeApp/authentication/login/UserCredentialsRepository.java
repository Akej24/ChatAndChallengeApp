package com.akej24.chatAndChallengeApp.authentication.login;

import com.akej24.chatAndChallengeApp.authentication.login.value_objects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserCredentialsRepository extends IUserCredentialsRepository, JpaRepository<UserCredentials, UserId> {

}




