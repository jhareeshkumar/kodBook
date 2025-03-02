package com.kodbook.exception.custom;

public class SamePasswordException extends RuntimeException {
    
    public SamePasswordException(String message) {
	super(message);
    }   
}
