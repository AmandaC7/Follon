package br.com.follon.demo.controller;

import br.com.follon.demo.request.RegisterRequest;
import br.com.follon.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/follon")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(RegisterRequest registerRequest) {

        service.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

}
