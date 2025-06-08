package com.fedesan14.repefit_api.services.interfaces;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.controllers.responses.SignInResponse;

public interface AuthService {
    void signup(CreateUserRequest createUserRequest);

    SignInResponse signIn(String username);
}
