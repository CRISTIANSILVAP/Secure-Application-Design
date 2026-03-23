package co.edu.escuelaing.apigateway.secureapp.security;

import co.edu.escuelaing.apigateway.secureapp.dto.ErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public CustomAccessDeniedHandler() {
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                Instant.now(),
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                "You do not have permission to access this resource",
                request.getRequestURI()
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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

