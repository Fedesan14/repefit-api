package com.fedesan14.repefit_api.security.converters;

import com.fedesan14.repefit_api.security.authentications.BearerTokenAuthentication;
import com.fedesan14.repefit_api.services.interfaces.JsonWebTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BearerTokenConverter implements AuthenticationConverter {

    private final JsonWebTokenService jsonWebTokenService;
    private final String BEARER_PREFIX = "Bearer ";

    public BearerTokenConverter(JsonWebTokenService jsonWebTokenService) {
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeaderIsInvalid(authorizationHeader)) return null;

        return BearerTokenAuthentication.unauthenticated(getSubject(getJwtValue(authorizationHeader)), authorizationHeader);
    }

    private String getSubject(String jwt) {
        return jsonWebTokenService.getSubject(jwt);
    }

    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeaderIsBlankOrNull(authorizationHeader) ||
                authorizationHeaderDoesNotContainsBearerPrefix(authorizationHeader) ||
                jwtIsInvalid(authorizationHeader);
    }

    private static boolean authorizationHeaderIsBlankOrNull(String authorizationHeader) {
        return authorizationHeader == null || authorizationHeader.isBlank();
    }

    private boolean authorizationHeaderDoesNotContainsBearerPrefix(String authorizationHeader) {
        return !StringUtils.startsWithIgnoreCase(authorizationHeader, BEARER_PREFIX);
    }

    private boolean jwtIsInvalid(String authorizationHeader) {
        return !jsonWebTokenService.isValid(getJwtValue(authorizationHeader));
    }

    private String getJwtValue(String authorizationHeader) {
        return authorizationHeader.substring(BEARER_PREFIX.length());
    }
}
