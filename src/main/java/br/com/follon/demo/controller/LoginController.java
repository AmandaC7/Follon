package br.com.follon.demo.controller;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.request.LoginRequest;
import br.com.follon.demo.response.LoginResponse;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.security.UserSecurityAdapter;
import br.com.follon.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follon")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final LoginService service;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                request.login(),
                request.password()
        );

        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserSecurityAdapter userDetails = (UserSecurityAdapter) auth.getPrincipal();
        UserEntity user = userDetails.user();

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
