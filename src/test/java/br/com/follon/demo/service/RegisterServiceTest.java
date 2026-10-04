package br.com.follon.demo.service;

import br.com.follon.demo.entity.FieldOfInterestEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.mapper.FieldOfInterestMapper;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.FieldOfInterestRepository;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.request.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FieldOfInterestRepository fieldOfInterestRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final FieldOfInterestMapper fieldOfInterestMapper = Mappers.getMapper(FieldOfInterestMapper.class);

    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        registerService = new RegisterService(userRepository, fieldOfInterestRepository,
                userMapper, fieldOfInterestMapper, passwordEncoder);
    }

    @Test
    void registerUser_whenValidRequest_shouldEncodePasswordAndSaveUser() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Ana");
        request.setEmail("ana@email.com");
        request.setPassword("secret");
        request.setFieldOfInterest(List.of(createFieldOfInterest()));

        when(userRepository.findByEmail("ana@email.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");

        registerService.registerUser(request);

        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals("encoded-secret", userCaptor.getValue().getPassword());
        assertEquals("ana@email.com", userCaptor.getValue().getEmail());
    }

    @Test
    void registerUser_whenNameIsBlank_shouldThrowIllegalArgumentException() {
        RegisterRequest request = new RegisterRequest();
        request.setName("");
        request.setEmail("ana@email.com");
        request.setPassword("secret");
        request.setFieldOfInterest(List.of(createFieldOfInterest()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> registerService.registerUser(request));

        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void registerUser_whenExistingUser_shouldThrowIllegalArgumentException() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Ana");
        request.setEmail("ana@email.com");
        request.setPassword("secret");
        request.setFieldOfInterest(List.of(createFieldOfInterest()));

        when(userRepository.findByEmail("ana@email.com")).thenReturn(Optional.of(new UserEntity()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> registerService.registerUser(request));

        assertEquals("User already exists", exception.getMessage());
    }

    @Test
    void registerFieldOfInterest_whenFieldAlreadyExists_shouldThrowIllegalArgumentException() {
        FieldOfInterestRequest request = createFieldOfInterest();

        when(fieldOfInterestRepository.findAll()).thenReturn(List.of(new FieldOfInterestEntity(1L, "Java")));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> registerService.registerFieldOfInterest(List.of(request)));

        assertEquals("Field of interest already exists", exception.getMessage());
    }

    private FieldOfInterestRequest createFieldOfInterest() {
        FieldOfInterestRequest fieldOfInterestRequest = new FieldOfInterestRequest();
        fieldOfInterestRequest.setName("Java");
        return fieldOfInterestRequest;
    }
}
