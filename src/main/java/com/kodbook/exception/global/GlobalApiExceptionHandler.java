package com.kodbook.exception.global;

import com.kodbook.authservice.exception.EmailSendFailureException;
import com.kodbook.authservice.exception.IncorrectPasswordException;
import com.kodbook.authservice.exception.InvalidOtpException;
import com.kodbook.authservice.exception.SamePasswordException;
import com.kodbook.dto.ErrorDto;
import com.kodbook.dto.response.ErrorResponse;
import com.kodbook.dto.response.ValidationErrorResponse;
import com.kodbook.exception.custom.UsernameNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalApiExceptionHandler {
    @ExceptionHandler(exception = UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e,
                                                                         HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(exception = InvalidOtpException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOtpException(InvalidOtpException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

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
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            if (!errors.containsKey(fieldError.getField())) {
                errors.put(fieldError.getField(), new ArrayList<>());
            }
            errors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
        }
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.setSuccess(false);
        validationErrorResponse.setMessage("Validation Failed");
        validationErrorResponse.setErrors(errors);
        log.error("Validation Failed :{}", exception.getMessage());
        return ResponseEntity.badRequest().body(validationErrorResponse);
    }

    @ExceptionHandler(exception = EmailSendFailureException.class)
    public ResponseEntity<ErrorResponse> handleEmailSendFailureException(EmailSendFailureException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
