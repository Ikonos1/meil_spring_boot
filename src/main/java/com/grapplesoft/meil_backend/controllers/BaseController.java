package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.models.request.BaseSendMail;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.mailService.MailService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {


//    public BaseController(MailService mailService) {
//        this.mailService = mailService;
//    }


    /**
     * @param ex MethodArgumentNotValidException
     * @return ApiResponse stating missing fields
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleValidationExceptions(@NotNull MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), errors, "Fields missing!"
        );
    }


}
