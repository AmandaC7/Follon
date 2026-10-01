package br.com.follon.demo.controller;

import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(value = "/follon")
public class RegisterFieldOfInterestController {

    private final RegisterService service;

    ResponseEntity<String> registerFieldOfInterest(FieldOfInterestRequest fieldOfInterestRequest) {

        service.registerFieldOfInterest(fieldOfInterestRequest);
        return ResponseEntity.ok("Field of interest registered successfully");
    }

}
