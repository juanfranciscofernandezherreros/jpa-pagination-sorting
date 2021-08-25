package com.javatechie.jpa.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWrapper {

    private String name;
    private String surname;
    private String quantity;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

}
