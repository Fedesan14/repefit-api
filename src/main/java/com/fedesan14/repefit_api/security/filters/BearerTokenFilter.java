package com.fedesan14.repefit_api.security.filters;

import com.fedesan14.repefit_api.security.converters.BearerTokenConverter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class BearerTokenFilter extends OncePerRequestFilter {

    private SecurityContextHolderStrategy securityContextHolderStrategy;
    private AuthenticationManager authenticationManager;
    private BearerTokenConverter bearerTokenConverter;
    private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    public BearerTokenFilter(AuthenticationManager authenticationManager, BearerTokenConverter bearerTokenConverter) {
        this.securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();;
        this.authenticationManager = authenticationManager;
        this.bearerTokenConverter = bearerTokenConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication auth = this.bearerTokenConverter.convert(request);
            if (auth == null) {
                this.logger.trace("Did not process authentication request since failed to find JWT in Authorization Header");
                filterChain.doFilter(request, response);
                return;
            }

            Authentication authentication = this.authenticationManager.authenticate(auth);
            SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, request, response);
        } catch (ExpiredJwtException e) {
            logger.info("Session is expired");
            this.securityContextHolderStrategy.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
