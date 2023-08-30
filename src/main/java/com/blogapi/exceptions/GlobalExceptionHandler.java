package com.blogapi.exceptions;

import com.blogapi.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // any exception create releted to ResourceNotFoundException then it can handle by  this class .(ResourceNotFoundException.class)
//    @ExceptionHandler(ResourceNotFoundException.class) // acts like catch block // ResourceNotFoundException  this is specific exception. it can handle only ResourceNotFoundException
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
//
//
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));//webRequest.getDescription(false) it will check true or false both.
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(Exception.class) // acts like catch block // ResourceNotFoundException  this is specific exception. it can handle only ResourceNotFoundException
    public ResponseEntity<ErrorDetails> GlobalException(Exception exception, WebRequest webRequest) { // WebRequest is bildin class object.


        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));//webRequest.getDescription(false) it will check true or false both.
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    } // global exception

}

