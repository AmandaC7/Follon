package br.com.follon.demo.service;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService(userRepository);
    }

    @Test
    void loginUser_whenUserExists_shouldNotThrow() {
        when(userRepository.findByEmail("user@email.com")).thenReturn(Optional.of(new UserEntity()));

        assertDoesNotThrow(() -> loginService.loginUser(new LoginRequest("user@email.com", "secret")));
    }

    @Test
    void loginUser_whenUserDoesNotExist_shouldThrowIllegalArgumentException() {
        when(userRepository.findByEmail("missing@email.com")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> loginService.loginUser(new LoginRequest("missing@email.com", "secret"))
        );

        org.junit.jupiter.api.Assertions.assertEquals("User with email missing@email.com does not exist.", exception.getMessage());
    }
}
