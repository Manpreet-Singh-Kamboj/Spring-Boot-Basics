package com.blogapp.exceptions;

import com.blogapp.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ApiResponseDto> emailAlreadyExistExceptionHandler(EmailAlreadyExistException emailAlreadyExistException){
        String errorMessage = emailAlreadyExistException.getMessage();
        ApiResponseDto apiResponseDto = new ApiResponseDto(false,errorMessage);
        return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.CONFLICT);
    }
}
