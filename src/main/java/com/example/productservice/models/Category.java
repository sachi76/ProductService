package com.example.productservice.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String name;

}
