package com.drcp.exception;

public class UnauthorizedException  extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

}