package com.fedesan14.repefit_api.services.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.data.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    void createUser(CreateUserRequest createUserRequest);

    User findByIdOrThrowException(UUID id);
}
