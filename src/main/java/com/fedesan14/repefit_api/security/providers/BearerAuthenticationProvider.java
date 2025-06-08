package com.fedesan14.repefit_api.security.providers;

import com.fedesan14.repefit_api.data.models.User;
import com.fedesan14.repefit_api.security.authentications.BearerTokenAuthentication;
import com.fedesan14.repefit_api.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BearerAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User) userService.loadUserByUsername(getUsername(authentication));
        return BearerTokenAuthentication.authenticated(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                user.getAuthorities()
        );
    }

    private String getUsername(Authentication authentication) {
        return authentication.getPrincipal().toString();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
