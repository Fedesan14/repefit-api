package com.fedesan14.repefit_api.services.implementations;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.services.interfaces.AuthService;
import com.fedesan14.repefit_api.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public void signup(CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

}
