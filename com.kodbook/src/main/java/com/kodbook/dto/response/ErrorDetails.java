package com.kodbook.dto.response;

import lombok.Data;

@Data
public class ErrorDetails {
    private String field;
    private String message;
}
