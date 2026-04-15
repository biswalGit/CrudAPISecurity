package com.pit.crudapp.jwt.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String errorType = (String) request.getAttribute("error");

        String message;

        if ("TOKEN_EXPIRED".equals(errorType)) {
            message = "JWT token has expired";
        } else if ("INVALID_TOKEN".equals(errorType)) {
            message = "JWT token is invalid";
        } else {
            message = "Authentication failed or token missing";
        }

        String json = """
                {
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "%s"
                }
                """.formatted(message);

        response.getWriter().write(json);

    }
}
