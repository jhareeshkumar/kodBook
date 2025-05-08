package com.kodbook.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {
    private final Instant timestamp = Instant.now();
    private Boolean success;
    private String message;
    private ErrorDetails errors;
}
