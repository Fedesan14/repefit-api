package com.fedesan14.repefit_api.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to register user in the application")
public record CreateUserRequest(
        @Schema(example = "username")
        @NotBlank
        String username,
        @Schema(example = "password")
        @NotBlank
        @Size(min = 5, message = "La longitud de la contraseña debe ser minimo 5 caracteres")
        String password,
        @Schema(example = "password")
        @NotBlank
        String repeatedPassword
) {
}
