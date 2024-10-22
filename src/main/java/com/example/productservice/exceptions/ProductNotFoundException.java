package com.example.productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception{
    private long id;

    public ProductNotFoundException(String message, long id){
        super(message);
        this.id = id;
    }


}
