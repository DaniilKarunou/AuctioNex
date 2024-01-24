package com.auctionex.exception.auth;

import org.springframework.security.authentication.BadCredentialsException;

public class UserBadCredentialsException extends BadCredentialsException {

    public UserBadCredentialsException(String userEmail) {
        super(String.format("Bad credentials for user with email: %s", userEmail));
    }
}
