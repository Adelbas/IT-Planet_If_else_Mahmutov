package ru.adel.if_else_task_2.core.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.adel.if_else_task_2.public_interface.exception.dto.ErrorResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {
        log.error(e.getLocalizedMessage(),e);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid request or authentication data")
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat();
        objectMapper.setDateFormat(df);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}