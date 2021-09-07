package com.javatechie.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private int quantity;
    private BigDecimal price;

    public Product(String name,String surname, int quantity, BigDecimal price) {
        this.name = name;
        this.surname = surname;
        this.quantity = quantity;
        this.price = price;
    }
}