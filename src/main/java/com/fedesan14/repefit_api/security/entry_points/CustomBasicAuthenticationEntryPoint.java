package com.fedesan14.repefit_api.security.entry_points;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedesan14.repefit_api.exceptions.responses.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Primary
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private final ObjectMapper mapper;

    public CustomBasicAuthenticationEntryPoint(ObjectMapper mapper) {
        super.setRealmName("Repefit-Api");
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(
                buildApiError(request, authException)
        ));
    }

    private static ApiError buildApiError(HttpServletRequest request, AuthenticationException authException) {
        return new ApiError(
                SC_UNAUTHORIZED,
                getErrorMessage(authException),
                request.getRequestURI(),
                LocalDateTime.now(),
                Arrays.asList(authException.getStackTrace())
        );
    }

    private static String getErrorMessage(AuthenticationException authException) {
        String errorMessage = "Hubo un error al autenticar. Intente nuevamente";
        if (authException instanceof BadCredentialsException || authException instanceof InternalAuthenticationServiceException) {
            errorMessage = "Las credenciales ingresadas son incorrectas";
        }
        return errorMessage;
    }

}
