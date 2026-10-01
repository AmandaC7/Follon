package br.com.follon.demo.service;

import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    public void loginUser(LoginRequest loginRequest) {

        validateUserExistence(loginRequest.getEmail());

    }

    private void validateUserExistence(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("User with email " + email + " does not exist.");
        }
    }
}
