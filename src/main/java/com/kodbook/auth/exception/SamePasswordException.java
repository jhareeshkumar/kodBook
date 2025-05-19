package com.kodbook.auth.exception;

public class SamePasswordException extends RuntimeException {

    public SamePasswordException(String message) {
        super(message);
    }
}
