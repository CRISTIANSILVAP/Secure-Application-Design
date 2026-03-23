package co.edu.escuelaing.apigateway.secureapp.controller;

import co.edu.escuelaing.apigateway.secureapp.dto.LoginRequestDto;
import co.edu.escuelaing.apigateway.secureapp.dto.LoginResponseDto;
import co.edu.escuelaing.apigateway.secureapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request.username(), request.password()));
    }
}

