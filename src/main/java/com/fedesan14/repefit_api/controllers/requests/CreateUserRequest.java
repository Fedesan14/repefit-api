package com.fedesan14.repefit_api.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 1)
        String password,
        @NotBlank
        String repeatedPassword
) {
}
