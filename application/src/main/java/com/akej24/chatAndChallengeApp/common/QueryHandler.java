package com.akej24.chatAndChallengeApp.common;

@FunctionalInterface
public interface QueryHandler<T, R> {

    R handle(T t);

}
