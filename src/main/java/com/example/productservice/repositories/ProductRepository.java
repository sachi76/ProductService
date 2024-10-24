package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //product repo should contain all the methods (CRUD) related to product model


    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();
}
