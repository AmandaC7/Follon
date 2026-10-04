package br.com.follon.demo.security;

import br.com.follon.demo.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret");
    }

    @Test
    void generateToken_shouldProduceValidToken() {
        UserEntity user = new UserEntity();
        user.setEmail("user@email.com");

        String token = tokenService.generateToken(user);

        assertFalse(token.isBlank());
        assertEquals("user@email.com", tokenService.validateToken(token));
    }

    @Test
    void validateToken_whenTokenIsBlank_shouldReturnEmptyString() {
        assertEquals("", tokenService.validateToken("   "));
        assertEquals("", tokenService.validateToken(null));
    }

    @Test
    void validateToken_whenTokenIsInvalid_shouldReturnEmptyString() {
        assertEquals("", tokenService.validateToken("invalid-token"));
    }
}
