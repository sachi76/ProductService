package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity responseEntity = new ResponseEntity<>(
         productService.getSingleproduct(id),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @PostMapping()
    public Product addProduct(){
        return null;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Something went wrong, inside product Cont",
                HttpStatus.NOT_FOUND
        );

        return response;
    }




}
