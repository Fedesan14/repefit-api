package com.fedesan14.repefit_api.services.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(CreateUserRequest createUserRequest);
}
