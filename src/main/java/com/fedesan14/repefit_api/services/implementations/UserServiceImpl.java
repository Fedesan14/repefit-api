package com.fedesan14.repefit_api.services.implementations;

import com.fedesan14.repefit_api.controllers.requests.CreateUserRequest;
import com.fedesan14.repefit_api.data.models.User;
import com.fedesan14.repefit_api.data.repositories.UserRepository;
import com.fedesan14.repefit_api.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        validatePasswordsAndThrowException(createUserRequest.password(), createUserRequest.repeatedPassword());

        try {
            userRepository.save(
                    new User(createUserRequest.username(), passwordEncoder.encode(createUserRequest.password()))
            );
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe", e.getCause());
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió algo inesperado", e.getCause());
        }
    }


    private void validatePasswordsAndThrowException(String password, String repeatedPassword) {
        if(!password.equals(repeatedPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las contraseñas no coinciden");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
