package com.example.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{


    private String title;
    private double price;
    private Category category;
    private String description;
    private String imageUrl;


}
