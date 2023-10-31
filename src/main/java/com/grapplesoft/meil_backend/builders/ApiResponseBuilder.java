package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.response.ApiResponse;
import org.springframework.http.HttpStatus;

/**
 * ApiResponseBuilder
 *
 * @author Vishwesh Shukla
 */
public class ApiResponseBuilder {

    // success messages
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .data(data)
                .message("Success!")
                .build();
    }

    public static <T> ApiResponse<T> success(HttpStatus httpStatus, T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode(httpStatus.value())
                .data(data)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .data(data)
                .message(message)
                .build();
    }


    // bad request messages
    public static <T> ApiResponse<T> badRequest() {
        return ApiResponse.<T>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(null)
                .message("Bad Request!")
                .build();
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return ApiResponse.<T>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(null)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> badRequest(T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(data)
                .message(message)
                .build();
    }
}
