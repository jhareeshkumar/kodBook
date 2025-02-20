package com.kodbook.exception.custom;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message) {
	super(message);
    }
}
