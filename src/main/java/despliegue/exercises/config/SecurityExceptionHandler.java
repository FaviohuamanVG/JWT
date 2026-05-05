package despliegue.exercises.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
            throws IOException {
        // Error 401: No autenticado o Token inválido/expirado
        handleError(response, HttpServletResponse.SC_UNAUTHORIZED, "No autorizado", authException.getMessage());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) 
            throws IOException {
        // Error 403: Autenticado pero sin permisos (rol insuficiente)
        handleError(response, HttpServletResponse.SC_FORBIDDEN, "Acceso denegado", "No tienes permisos para acceder a este recurso");
    }

    private void handleError(HttpServletResponse response, int status, String error, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("error", error);
        body.put("message", message);
        body.put("path", "check your request path");

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
