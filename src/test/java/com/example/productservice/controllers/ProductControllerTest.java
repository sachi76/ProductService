package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;




@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;





    @Test
    void testGetProductByIdPositive() {

    }

    @Test
    void testGetProductByIdNegative() {

    }

    @Test
    void testGetProductByIdThrowsException() {

    }

    @Test
    void testGetAllProducts() {

        List<Product> expectedProducts = new ArrayList<>();

        Product product1 = new Product();
        product1.setTitle("Iphone 16");
        product1.setPrice(100000.00);

        Product product2 = new Product();
        product2.setTitle("Iphone 15");
        product2.setPrice(90000.00);

        Product product3 = new Product();
        product3.setTitle("Iphone 13");
        product3.setPrice(90000.00);

        expectedProducts.add(product1);
        expectedProducts.add(product2);
        expectedProducts.add(product3);

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        List<Product> actualProducts = productController.getAllProducts();

        assertEquals(expectedProducts.size(), actualProducts.size());

        for(int i = 0; i < expectedProducts.size(); i++){
            assertEquals(expectedProducts.get(i), actualProducts.get(i));
        }


    }
}