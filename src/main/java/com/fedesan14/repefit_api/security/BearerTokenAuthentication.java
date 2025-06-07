package com.fedesan14.repefit_api.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BearerTokenAuthentication extends AbstractAuthenticationToken {
    private final Object principal;
    private Object credentials;

    public BearerTokenAuthentication(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    public BearerTokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public static BearerTokenAuthentication unauthenticated(Object principal, Object credentials) {
        return new BearerTokenAuthentication(principal, credentials);
    }

    public static BearerTokenAuthentication authenticated(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        return new BearerTokenAuthentication(principal, credentials, authorities);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
