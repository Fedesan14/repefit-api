package com.fedesan14.repefit_api.services.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;

public interface AuthService {
    void signup(CreateUserRequest createUserRequest);
}
