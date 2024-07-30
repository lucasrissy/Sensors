package com.example.temperature.exceptions;


import com.example.temperature.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleGlobalException(Exception exception, WebRequest webRequest){

        ResponseErrorDto responseErrorDto = new ResponseErrorDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResponseErrorDto> resourceNotFound(ResourceNotFound resourceNotFound, WebRequest webRequest){

        ResponseErrorDto responseErrorDto = new ResponseErrorDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                resourceNotFound.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
