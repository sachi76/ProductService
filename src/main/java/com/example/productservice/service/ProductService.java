package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getSingleproduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
}
