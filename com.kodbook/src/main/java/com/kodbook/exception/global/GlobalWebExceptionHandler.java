package com.kodbook.exception.global;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kodbook.dto.ErrorDto;
import com.kodbook.exception.custom.IncorrectPasswordException;
import com.kodbook.exception.custom.SamePasswordException;

@ControllerAdvice
public class GlobalWebExceptionHandler {

    @ExceptionHandler(exception = IncorrectPasswordException.class)
    public ResponseEntity<ErrorDto> handleIncorrectPasswordException(IncorrectPasswordException exception) {
	ErrorDto errorDto = new ErrorDto();
	errorDto.setCode(HttpStatus.BAD_REQUEST.value());
	errorDto.setMessage(exception.getMessage());
	errorDto.setReason(HttpStatus.BAD_REQUEST.getReasonPhrase());
	return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = SamePasswordException.class)
    public ResponseEntity<ErrorDto> handleSamePasswordException(SamePasswordException exception) {
	ErrorDto errorDto = new ErrorDto(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
		HttpStatus.BAD_REQUEST.getReasonPhrase());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
	Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(key->key.getField(), value->value.getDefaultMessage()));
	return ResponseEntity.badRequest().body(errors);
    }
}
