package com.javatechie.jpa.service;

import com.javatechie.jpa.dto.ProductDTO;
import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.utils.ObjectMapperUtils;
import com.javatechie.jpa.wrapper.ProductWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 18)
                .mapToObj(i -> new Product("alejandro".toLowerCase(), RandomStringUtils.randomAlphabetic(10).toLowerCase() ,new Random().nextInt(100), BigDecimal.valueOf(new Random().nextInt(50000))))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }

    public Page<ProductDTO> AndSpecification(final Pageable pageable, final ProductWrapper productWrapper) {
        Specification<Product> productSpecsSpecification =
                ProductSpecs.name(productWrapper.getName())
                        .and(ProductSpecs.surname(productWrapper.getSurname()))
                        .and(ProductSpecs.price(productWrapper.getPriceFrom(),productWrapper.getPriceTo()))
                        .and(ProductSpecs.quantity(productWrapper.getQuantity()));
        Page<Product> pageProducts = productRepository.findAll(productSpecsSpecification,pageable);
        return ObjectMapperUtils.mapEntityPageIntoDtoPage(pageProducts,ProductDTO.class);
    }




}
