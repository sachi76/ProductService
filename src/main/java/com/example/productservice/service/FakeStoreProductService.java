package com.example.productservice.service;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }



    @Override
    public Optional<Product> getSingleproduct(Long productId) throws ProductNotFoundException {

       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
                );

       //convert fakeStoreProductDto to product


        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id" + productId + "is not present", productId);
        }

        return Optional.of(convertFakeStoreProductToProduct(fakeStoreProductDto));

    }

//    @Override
//    public Page<Product> getAllProducts( int pageNumber,  int pageSize) {
//        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
//            FakeStoreProductDto[].class
//                );
//
//        //convert all fakeStoreProducts into products
//        List<Product> products = new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
//            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
//        }
//        int start = pageNumber * pageSize;
//        int end = Math.min(start + pageSize, products.size());
//
//        // Check if start index is within bounds
//        if (start > products.size()) {
//            return new PageImpl<>(new ArrayList<>(), PageRequest.of(pageNumber, pageSize), products.size());
//        }
//
//        // Create a sublist for the paginated data
//        List<Product> paginatedProducts = products.subList(start, end);
//
//        // Return paginated data as a PageImpl object
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return new PageImpl<>(paginatedProducts, pageable, products.size());
//
//    }

    @Override
    public List<Product> getAllProducts() {
    return null;

    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product updateProduct(Long id, Product product) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }


    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
