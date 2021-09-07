package com.javatechie.jpa.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String surname;
    private int quantity;
    private BigDecimal price;

}
