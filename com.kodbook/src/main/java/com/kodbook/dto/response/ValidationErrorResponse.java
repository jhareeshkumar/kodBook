package com.kodbook.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
public class ValidationErrorResponse {
    private Instant timestamp = Instant.now();
    private boolean success;
    private String message;
    private Map<String, List<String>> errors;
}
