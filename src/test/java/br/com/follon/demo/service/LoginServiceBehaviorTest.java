package br.com.follon.demo.service;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceBehaviorTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    LoginService loginService;

    @Test
    void shouldAcceptExistingUser() {
        when(userRepository.findByEmail("user@email.com")).thenReturn(Optional.of(new UserEntity()));

        assertDoesNotThrow(() -> loginService.loginUser(new LoginRequest("user@email.com", "secret")));
    }
}
