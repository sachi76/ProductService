package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Optional<Product> getSingleproduct(Long productId) throws ProductNotFoundException;
//    Page<Product> getAllProducts(int pageNumber, int pageSize);

    List<Product> getAllProducts();


    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;
    Product addProduct(Product product);
}
