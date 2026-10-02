package br.com.follon.demo.controller;

import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follon")
public class RegisterFieldOfInterestController {

    private final RegisterService service;

    @PostMapping("/register-field-of-interest")
    public ResponseEntity<String> registerFieldOfInterest(FieldOfInterestRequest fieldOfInterestRequest) {

        service.registerFieldOfInterest(fieldOfInterestRequest);
        return ResponseEntity.ok("Field of interest registered successfully");
    }

}
