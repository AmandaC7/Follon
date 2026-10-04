package br.com.follon.demo.service;

import br.com.follon.demo.entity.FieldOfInterestEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.mapper.FieldOfInterestMapper;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.FieldOfInterestRepository;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final FieldOfInterestRepository fieldOfInterestRepository;
    private final UserMapper userMapper;
    private final FieldOfInterestMapper fieldOfInterestMapper;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest) {

        validateRegisterRequest(registerRequest);
        doesUserExist(registerRequest.getEmail());
        saveNewUser(registerRequest);

    }

    private void validateRegisterRequest(RegisterRequest registerRequest) {
        if (registerRequest.getName() == null || registerRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (registerRequest.getFieldOfInterest() == null) {
            throw new IllegalArgumentException("Field of interest is required");
        }

    }

    private void doesUserExist(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
    }

    private void saveNewUser(RegisterRequest registerRequest) {
        UserEntity user;
        user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User already exists");
        }
    }

    public void registerFieldOfInterest(List<FieldOfInterestRequest> fieldOfInterests) {

        for (FieldOfInterestRequest fieldOfInterest : fieldOfInterests) {
            if (fieldOfInterest == null || fieldOfInterest.getName().isEmpty()) {
                throw new IllegalArgumentException("Field of interest is required");
            }

            Optional<FieldOfInterestEntity> fieldOfInterestEntity = Optional.empty();
            List<FieldOfInterestEntity> name = fieldOfInterestRepository.findAll();

            for (FieldOfInterestEntity field : name) {
                if (field.getName().equals(fieldOfInterest.getName())) {
                    fieldOfInterestEntity = Optional.of(field);
                    break;
                }
            }

            if (fieldOfInterestEntity.isPresent()) {
                throw new IllegalArgumentException("Field of interest already exists");
            }

            fieldOfInterestRepository.save(fieldOfInterestMapper.toEntity(fieldOfInterest));

        }
    }

    public List<FieldOfInterestEntity> getFieldOfInterest(List<FieldOfInterestRequest> fieldOfInterests) {
        return fieldOfInterestRepository.findAllByNameIn(fieldOfInterests.stream().map(FieldOfInterestRequest::getName).toList());
    }

}
