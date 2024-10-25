package com.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{


    private String title;
    private Double price;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;
    private String description;
    private String imageUrl;


}
