package com.fedesan14.repefit_api.controllers.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.controllers.responses.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpHeaders;

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

    @Operation(
            summary = "Sign in with an registered user",
            security = @SecurityRequirement(name = "basicAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The user has logged in correctly",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SignInResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Username or password are wrong")
    })
    @PostMapping("/sign-in")
    SignInResponse signIn(
            @Parameter(
                    name = "Authorization",
                    description = "Authorization header with format 'Basic base64(username:password)'",
                    in = ParameterIn.HEADER,
                    required = true,
                    example = "Basic dXNlcm5hbWU6cGFzc3dvcmQ="
            )
            @RequestHeader(name = "authorization")
            String authorization
    );
}
