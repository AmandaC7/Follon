package br.com.follon.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RegisterFieldOfInterestServiceTest {
    @Test
    void shouldDoNothing() {
        RegisterFieldOfInterestService service = new RegisterFieldOfInterestService();
        assertDoesNotThrow(() -> service.registerFieldOfInterest("Java"));
    }
}
