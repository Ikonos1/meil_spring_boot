package com.grapplesoft.meil_backend.models.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private int statusCode;
    private T data;
    private String message;
}
