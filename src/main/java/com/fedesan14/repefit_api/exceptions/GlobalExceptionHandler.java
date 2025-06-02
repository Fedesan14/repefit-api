package com.fedesan14.repefit_api.exceptions;

import com.fedesan14.repefit_api.exceptions.responses.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException responseStatusException, HttpServletRequest request) {
        return ResponseEntity
                .status(responseStatusException.getStatusCode())
                .body(ApiError.builder()
                        .status(responseStatusException.getStatusCode().value())
                        .errorMessage(responseStatusException.getReason())
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .trace(Arrays.asList(responseStatusException.getCause().getStackTrace()))
                        .build()
                );
    }

}
