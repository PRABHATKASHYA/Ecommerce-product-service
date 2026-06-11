package com.product_service.repository;

import com.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByBrandName(String brandName);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findBySubCategoryName(String subCategoryName);

    List <Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
