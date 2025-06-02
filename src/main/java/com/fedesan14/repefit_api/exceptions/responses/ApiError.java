package com.fedesan14.repefit_api.exceptions.responses;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(
        int status,
        String errorMessage,
        String path,
        LocalDateTime timestamp,
        List<StackTraceElement> trace
) {}
