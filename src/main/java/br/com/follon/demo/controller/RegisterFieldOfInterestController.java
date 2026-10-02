package br.com.follon.demo.controller;

import br.com.follon.demo.entity.FieldOfInterestEntity;
import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follon")
public class RegisterFieldOfInterestController {

    private final RegisterService service;

    @PostMapping("/register-field-of-interest")
    public ResponseEntity<String> registerFieldOfInterest(@RequestBody List<FieldOfInterestRequest> fieldOfInterestRequest) {

        service.registerFieldOfInterest(fieldOfInterestRequest);
        return ResponseEntity.ok("Field of interest registered successfully");
    }

    @GetMapping("/register-field-of-interest")
    public ResponseEntity<List<FieldOfInterestEntity>> getFieldOfInterest(@RequestBody List<FieldOfInterestRequest> fieldOfInterestRequest) {

        List<FieldOfInterestEntity> fieldOfInterests = service.getFieldOfInterest(fieldOfInterestRequest);
        return ResponseEntity.ok(fieldOfInterests);
    }
}
