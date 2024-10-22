package com.example.productservice.service;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getSingleproduct(Long productId);
    List<Product> getAllProducts();
}
