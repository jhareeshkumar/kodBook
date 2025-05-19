package com.kodbook.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "Model of error response")
public class ErrorResponse {
    private final Instant timestamp = Instant.now();
    private int status;
    private String error;
    private String message;
    private String path;
}
