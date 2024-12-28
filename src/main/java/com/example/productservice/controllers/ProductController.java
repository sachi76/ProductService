package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
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

//    @GetMapping()
//    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pagenumber")int pageNumber, @RequestParam("pagesize")  int pageSize){
//        Page    <Product> products = productService.getAllProducts(pageNumber,pageSize);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
         productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {

        Product updatedProduct =  productService.updateProduct(id, product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED).getBody();

    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        Product replacedProduct = productService.replaceProduct(id, product);

        return new ResponseEntity<>(replacedProduct, HttpStatus.ACCEPTED).getBody();
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addProduct(product);
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
