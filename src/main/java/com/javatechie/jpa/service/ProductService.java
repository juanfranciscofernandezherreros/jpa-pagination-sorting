package com.javatechie.jpa.service;

import com.javatechie.jpa.dto.ProductDTO;
import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.repository.ProductRepository;
import com.javatechie.jpa.utils.ObjectMapperUtils;
import com.javatechie.jpa.wrapper.ProductWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 18)
                .mapToObj(i -> new Product("alejandro".toLowerCase(), RandomStringUtils.randomAlphabetic(10).toLowerCase() ,new Random().nextInt(100), BigDecimal.valueOf(new Random().nextInt(50000))))
                .collect(Collectors.toList());
        repository.saveAll(products);
    }

    public Page<ProductDTO> findAllProductsWithPageable(final Pageable pageable, final ProductWrapper productWrapper) {
        List<Sort.Order> sorts = getOrderList(pageable);
        return ObjectMapperUtils.mapEntityPageIntoDtoPage(repository.findAllQuery(
                productWrapper.getName(),
                productWrapper.getSurname(),
                productWrapper.getPriceFrom(),
                productWrapper.getPriceTo(),
                productWrapper.getQuantity(),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sorts))),ProductDTO.class);
    }

    private List<Sort.Order> getOrderList(Pageable pageable) {
        List<Sort.Order> sorts=new ArrayList<>();
        if (Objects.nonNull(pageable.getSort().getOrderFor("name"))){
            sorts.add(new Sort.Order(pageable.getSort().getOrderFor("name").getDirection(),"name"));
        }
        if (Objects.nonNull(pageable.getSort().getOrderFor("surname"))){
            sorts.add(new Sort.Order(pageable.getSort().getOrderFor("surname").getDirection(),"surname"));
        }
        if (Objects.nonNull(pageable.getSort().getOrderFor("quantity"))){
            sorts.add(new Sort.Order(pageable.getSort().getOrderFor("quantity").getDirection(),"quantity"));
        }
        if (Objects.nonNull(pageable.getSort().getOrderFor("price"))){
            sorts.add(new Sort.Order(pageable.getSort().getOrderFor("price").getDirection(),"price"));
        }
        return sorts;
    }
}
