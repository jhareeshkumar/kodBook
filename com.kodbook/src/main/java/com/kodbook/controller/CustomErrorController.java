package com.kodbook.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Profile(value = "prod")
@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "This API is disabled in production.");
        errorResponse.put("status", "404 NOT FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
