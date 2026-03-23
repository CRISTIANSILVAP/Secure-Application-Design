package co.edu.escuelaing.apigateway.secureapp.security;

import co.edu.escuelaing.apigateway.secureapp.dto.ErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public CustomAuthenticationEntryPoint() {
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                Instant.now(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                "Authentication is required to access this resource",
                request.getRequestURI()
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(toJson(errorResponse));
    }

    private String toJson(ErrorResponseDto error) {
        return "{"
                + "\"timestamp\":\"" + error.timestamp() + "\"," 
                + "\"status\":" + error.status() + ","
                + "\"error\":\"" + error.error() + "\"," 
                + "\"message\":\"" + error.message() + "\"," 
                + "\"path\":\"" + error.path() + "\""
                + "}";
    }
}

