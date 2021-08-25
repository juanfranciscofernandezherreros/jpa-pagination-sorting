package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.ProductDTO;
import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.service.ProductService;
import com.javatechie.jpa.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    private ResponseEntity<Page<ProductDTO>> findAllProductsWithPageable(ProductWrapper productWrapper,
                                                                         @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.findAllProductsWithPageable(pageable,productWrapper));
    }

}
