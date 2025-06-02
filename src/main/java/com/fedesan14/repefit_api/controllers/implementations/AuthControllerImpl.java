package com.fedesan14.repefit_api.controllers.implementations;

import com.fedesan14.repefit_api.controllers.interfaces.AuthController;
import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.services.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public void signUp(CreateUserRequest createUserRequest) {
        authService.signup(createUserRequest);
    }
}
