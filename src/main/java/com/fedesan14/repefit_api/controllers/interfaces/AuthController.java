package com.fedesan14.repefit_api.controllers.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication")
@RequestMapping("/auth")
public interface AuthController {
    @Operation(summary = "Register user on the application")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Error because username is already used"),
            @ApiResponse(responseCode = "400", description = "Error because passwords does not match"),
            @ApiResponse(responseCode = "400", description = "Error because received data is wrong"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
    })
    @PostMapping("/signup")
    void signUp(@RequestBody @Valid CreateUserRequest createUserRequest);
}
