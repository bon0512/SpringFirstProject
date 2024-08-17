package com.example.firstproject.dto;


import com.example.firstproject.entity.Coffee;
import lombok.ToString;

import java.beans.ConstructorProperties;

@ToString
public class CoffeeDto {
    private Long id;
    private String name;
    private String price;

    @ConstructorProperties({"id","name","price"})

    public CoffeeDto(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Coffee toEntity(){
        return new Coffee(id,name,price);
    }
}
