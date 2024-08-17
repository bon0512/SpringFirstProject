package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Entity
@ToString
@NoArgsConstructor
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    @ConstructorProperties({"id", "name", "price"})
    public Coffee(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void patch(Coffee coffee) {
        if (coffee.name != null) {
            this.name = coffee.name;
        }
        if (coffee.price != null) {
            this.price = coffee.price;
        }
    }
}
