package com.kodbook.exception;

public class SamePasswordException extends RuntimeException {
    
    public SamePasswordException(String message) {
	super(message);
    }   
}