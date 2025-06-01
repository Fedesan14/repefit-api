package com.fedesan14.repefit_api.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Ping")
@RequestMapping("/ping")
public interface PingController {

    @Operation(summary = "Endpoint to check if the project is up")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Always returns pong message", content = { @Content(mediaType = "text/plain") }),
            @ApiResponse(responseCode = "500", description = "If service has a problem", content = { @Content(mediaType = "application/json") })
    })
    @GetMapping
    String ping();
}
