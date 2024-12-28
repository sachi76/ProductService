package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Product> getSingleproduct(Long productId) throws ProductNotFoundException {

        //make a call to db and fetch a product with given ID

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with ID: " + productId + "does not " +
                    "exist", productId);
        }
        return Optional.of(productOptional.get());
    }

//    @Override
//    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return productRepository.findAll(pageable);
//
//    }

    @Override
    public List<Product> getAllProducts() {


        return  productRepository.findAll();

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with Id: "  + id + "is not found", id);
        }
        Product productInDb = productOptional.get();

        if(product.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            productInDb.setDescription(product.getDescription());
        }

        if(product.getCategory() != null){
            productInDb.setCategory(product.getCategory());
        }

        if(product.getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }

        if(product.getImageUrl() != null){
            productInDb.setImageUrl(product.getImageUrl());
        }

        return productRepository.save(productInDb);

    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {

        if(productRepository.existsById(id)){
            product.setId(id);
            return productRepository.save(product);
        }else{
            throw new ProductNotFoundException("Product with id" + id +  "not found", id);
        }

    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();

        if( categoryRepository.existsByName(category.getName())){
            // we need to assign the id and name of the existing category
            Category existingCategory = categoryRepository.findByName(category.getName());
            product.setCategory(existingCategory);
        }else {
            // create a new category object with a new id
            categoryRepository.save(category);
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
}
