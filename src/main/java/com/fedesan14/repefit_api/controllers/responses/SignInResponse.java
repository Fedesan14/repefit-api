package com.fedesan14.repefit_api.controllers.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
        @Schema(description = "JWT to authenticate it with the server")
        String accessToken,
        @Schema(description = "JWT to refresh the access token when current one expires")
        String refreshToken
) {
}
