package com.custom.auth.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {
    private HttpStatus httpStatus;
    private String description;
    private LocalDateTime dateTime;
}
