package br.com.follon.demo.controller;

import br.com.follon.demo.request.RegisterRequest;
import br.com.follon.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follon")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {

        service.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

}
