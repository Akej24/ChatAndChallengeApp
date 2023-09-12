--liquibase formatted sql
--changeset akej:1

CREATE TABLE users (
    user_id UUID NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    encoded_password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    locked BOOLEAN NOT NULL,
    PRIMARY KEY(user_id)
);