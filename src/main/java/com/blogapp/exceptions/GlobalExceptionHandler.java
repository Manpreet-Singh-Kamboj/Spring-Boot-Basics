package com.blogapp.exceptions;

import com.blogapp.payloads.ApiResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ApiResponseDto> emailAlreadyExistExceptionHandler(EmailAlreadyExistException emailAlreadyExistException){
        String errorMessage = emailAlreadyExistException.getMessage();
        ApiResponseDto apiResponseDto = new ApiResponseDto(false,errorMessage);
        return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(ConstraintViolationException constraintViolationException){
        Map<String,String> response = new HashMap<String,String>();
        constraintViolationException.getConstraintViolations().forEach(violation-> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        String errorMessage = resourceNotFoundException.getMessage();
        ApiResponseDto apiResponseDto = new ApiResponseDto(false,errorMessage);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ApiResponseDto> invalidRoleExceptionHandler(InvalidRoleException invalidRoleException){
        String errorMessage = invalidRoleException.getMessage();
        ApiResponseDto apiResponseDto = new ApiResponseDto(false,errorMessage);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto> categoryAlreadyExistsHandler(CategoryAlreadyExistsException categoryAlreadyExistsException){
        String errorMessage = categoryAlreadyExistsException.getMessage();
        ApiResponseDto apiResponseDto = new ApiResponseDto(false,errorMessage);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.CONFLICT);
    }

}
