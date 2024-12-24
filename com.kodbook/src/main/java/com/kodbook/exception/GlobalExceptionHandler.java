package com.kodbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kodbook.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = IncorrectPasswordException.class)
    public ResponseEntity<ErrorDto> handleIncorrectPasswordException(IncorrectPasswordException exception) {
	ErrorDto errorDto = new ErrorDto();
	errorDto.setCode(HttpStatus.BAD_REQUEST.value());
	errorDto.setMessage(exception.getMessage());
	errorDto.setReason(HttpStatus.BAD_REQUEST.getReasonPhrase());
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(exception = SamePasswordException.class)
    public ResponseEntity<ErrorDto> handleSamePasswordException(SamePasswordException exception) {
	ErrorDto errorDto = new ErrorDto(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }
}

