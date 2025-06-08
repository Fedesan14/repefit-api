package com.fedesan14.repefit_api.services.implementations;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.controllers.responses.SignInResponse;
import com.fedesan14.repefit_api.services.interfaces.AuthService;
import com.fedesan14.repefit_api.services.interfaces.JsonWebTokenService;
import com.fedesan14.repefit_api.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JsonWebTokenService jsonWebTokenService;

    @Override
    public void signup(CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

    @Override
    public SignInResponse signIn(String username) {
        return new SignInResponse(
                jsonWebTokenService.getAccessToken(username),
                jsonWebTokenService.getRefreshToken(username)
        );
    }

}
