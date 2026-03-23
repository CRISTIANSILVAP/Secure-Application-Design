package co.edu.escuelaing.apigateway.secureapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SecureAppApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SecureAppApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "6000"));
                app.run(args);
    }

}
