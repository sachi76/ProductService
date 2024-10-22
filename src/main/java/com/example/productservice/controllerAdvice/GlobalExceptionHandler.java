package com.example.productservice.controllerAdvice;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ArithmeticException has happened");
        exceptionDto.setSolution("I do not know");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );

        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product with provided id" + exception.getId() + " is not found ");
        exceptionDto.setSolution("Try again with a valid product id ");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );

        return response;
    }

    // do for nullpointer and all other

}
