package com.javatechie.jpa.repository;

import com.javatechie.jpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    /**JPQL**/
    @Query("SELECT c FROM Product c" +
            " WHERE (:name is null or c.name like %:name%)" +
            " and (:surname is null or c.surname like %:surname%)" +
            " and (:priceTo is null and :priceFrom is null or c.price BETWEEN :priceFrom  and :priceTo)" +
            " and (:quantity is null or CAST( c.quantity as string ) like %:quantity%)")
    Page<Product> findAllQuery(@Param("name") String name,
                               @Param("surname") String surname,
                               @Param("priceFrom") BigDecimal priceFrom,
                               @Param("priceTo") BigDecimal priceTo,
                               @Param("quantity") String quantity,
                               Pageable pageable);
}
