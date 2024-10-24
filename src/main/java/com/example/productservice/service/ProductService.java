package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Optional<Product> getSingleproduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Product addProduct(Product product);
}
