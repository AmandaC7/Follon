package br.com.follon.demo.service;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.mapper.FieldOfInterestMapper;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.FieldOfInterestRepository;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.request.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceBehaviorTest {
    @Mock
    UserRepository userRepository;
    @Mock
    FieldOfInterestRepository fieldOfInterestRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final FieldOfInterestMapper fieldOfInterestMapper = Mappers.getMapper(FieldOfInterestMapper.class);
    private RegisterService registerService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        registerService = new RegisterService(userRepository, fieldOfInterestRepository, userMapper, fieldOfInterestMapper, passwordEncoder);
    }

    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Ana");
        request.setEmail("ana@email.com");
        request.setPassword("secret");
        request.setFieldOfInterest(List.of(new FieldOfInterestRequest()));

        when(userRepository.findByEmail("ana@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        registerService.registerUser(request);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());
        assertEquals("encoded-secret", captor.getValue().getPassword());
    }
}
