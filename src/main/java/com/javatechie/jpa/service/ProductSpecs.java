package com.javatechie.jpa.service;

import com.javatechie.jpa.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecs {

    public static Specification<Product> name (String linea) {

        return (root, query, criteriaBuilder) -> {
            if (linea == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(root.get("name"), "%"+linea+"%");
        };
    }

    public static Specification<Product> surname (String linea) {

        return (root, query, criteriaBuilder) -> {
            if (linea == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(root.get("surname"), "%"+linea+"%");
        };
    }

    public static Specification<Product> quantity (String linea) {

        return (root, query, criteriaBuilder) -> {
            if (linea == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(root.get("quantity").as(String.class), "%"+linea+"%");
        };
    }

    public static Specification<Product> price(BigDecimal priceFrom, BigDecimal priceTo) {
        return (root, query, criteriaBuilder) -> {
            if (priceFrom != null && priceTo == null) {
                return criteriaBuilder.between(root.get("price"),priceFrom,BigDecimal.valueOf(999999999));
            }
            if (priceFrom == null && priceTo != null) {
                return criteriaBuilder.between(root.get("price"),BigDecimal.ZERO,priceTo);
            }
            if (priceFrom != null && priceTo != null) {
                return criteriaBuilder.between(root.get("price"),priceFrom,priceTo);
            }
            return criteriaBuilder.conjunction();

        };
    }
}

