package co.edu.escuelaing.apigateway.secureapp.controller;

import co.edu.escuelaing.apigateway.secureapp.dto.MessageResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureHelloController {

    @GetMapping("/hello")
    public MessageResponseDto secureHello() {
        return new MessageResponseDto("Secure Hello World");
    }
}

