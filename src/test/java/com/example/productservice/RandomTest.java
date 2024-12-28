package com.example.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTest {

    @Test
    void testAddMethod(){
        //3A, Arrange, act, assert
        int a = 10;
        int b = 20;   //arrange

        int result = a + b;  //act

        assertEquals(30, result, "Result should be 30");

//        assert result == 30;
    }
}
